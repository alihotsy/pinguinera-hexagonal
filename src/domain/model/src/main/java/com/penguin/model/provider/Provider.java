package com.penguin.model.provider;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import com.penguin.model.provider.dto.Bill;
import com.penguin.model.provider.dto.LiteraryWork;
import com.penguin.model.provider.entity.Copy;
import com.penguin.model.provider.events.BookSaved;
import com.penguin.model.provider.events.CalculatedBill;
import com.penguin.model.provider.events.CalculatedBillGroup;
import com.penguin.model.provider.events.CalculatedMaxWholeDiscount;
import com.penguin.model.provider.values.copy.*;
import com.penguin.model.provider.values.identities.ProviderId;
import com.penguin.model.generic.AggregateRoot;
import com.penguin.model.generic.DomainEvent;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

public class Provider extends AggregateRoot<ProviderId> {

    protected List<Copy> copies;

    protected Map<LiteraryWork, Integer> copyStock;
    protected Copy result;

    private Provider(ProviderId providerId) {
        super(providerId);
        //subscribe(new BookStoreQuotesEventChange(this));
    }



    public Provider(Title title,
                    Author author,
                    AreaOfKnowledge areaOfKnowledge,
                    NumOfPages numOfPages,
                    CopiesOfTheBook copiesOfTheBook,
                    Price price,
                    BookType bookType) {
        super(new ProviderId());
        subscribe(new ProviderBehavior(this));
        appendChange(new BookSaved(
                identity().value(),
                title.value(),
                author.value(),
                areaOfKnowledge.value(),
                numOfPages.value(),
                copiesOfTheBook.value(),
                price.value(),
                bookType.value())).apply();
    }

    public void addCopy(Title title,
                        Author author,
                        AreaOfKnowledge areaOfKnowledge,
                        NumOfPages numOfPages,
                        CopiesOfTheBook copiesOfTheBook,
                        Price price,
                        BookType bookType) {
        subscribe(new ProviderBehavior(this));
        appendChange(new BookSaved(
                identity().value(),
                title.value(),
                author.value(),
                areaOfKnowledge.value(),
                numOfPages.value(),
                copiesOfTheBook.value(),
                price.value(),
                bookType.value())).apply();
    }

    public void addCopiesStock(Map<LiteraryWork, Integer> copyStock, LocalDate registeredAt) {
        subscribe(new ProviderBehavior(this));
        this.copyStock = copyStock;
        appendChange(new CalculatedBill(copyStock, registeredAt)).apply();
    }

    public void calculateMaxWholeSale(List<LiteraryWork> books, Double budget, LocalDate registeredAt) {
        subscribe(new ProviderBehavior(this));
        appendChange(new CalculatedMaxWholeDiscount(budget,books,registeredAt)).apply();
    }

    public void calculateBillGroup(List<Bill> bills) {
        subscribe(new ProviderBehavior(this));
        appendChange(new CalculatedBillGroup(bills)).apply();
    }

    public Integer calculateTotalCopies(List<LiteraryWork> books) {
        return books.stream()
                .map(LiteraryWork::getCopiesOfTheBook)
                .reduce(0, Integer::sum);
    }

    public Copy getResult() {
        return result;
    }

    public void setResult(Copy result) {
        this.result = result;
    }

    public static Provider from(ProviderId providerId, List<DomainEvent> events) {
        var bookStoreQuotes = new Provider(providerId);
        events.forEach(bookStoreQuotes::applyEvent);
        return bookStoreQuotes;
    }

    public double discountBySeniority(double totalPrice, LocalDate registeredAt) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(registeredAt, today);
        double years = period.getYears() + period.getMonths() / 12.0;

        RangeMap<Double, Double> discount = TreeRangeMap.create();
        discount.put(Range.closedOpen(0.0,1.0), totalPrice);
        discount.put(Range.closed(1.0,2.0), totalPrice - totalPrice*0.12);
        discount.put(Range.greaterThan(2.0), totalPrice - totalPrice*0.17);

        return discount.get(years);

    }

    public double totalPriceForAllCopiesOfOneBook(Integer quantity, LiteraryWork copy) {
        if(quantity > copy.getCopiesOfTheBook()) {
            throw new IllegalArgumentException("quantity exceeds than copy in stock");
        }
        if(quantity > 10) {
            final int COPIES_EXCLUDED = 10;
            int copiesForDiscount = quantity - COPIES_EXCLUDED;
            //System.out.println(copiesForDiscount);
            double totalDiscount = copiesForDiscount*0.0015;
            //book.getPrice()*10
            if(totalDiscount > 1) {
                totalDiscount = 0.99;
            }

            return (copy.getPrice() + copy.getPrice()*0.02)*COPIES_EXCLUDED + copiesForDiscount*(copy.getPrice() - copy.getPrice()*totalDiscount);
        }
        //book.getPrice()*book.getCopiesOfTheBook()
        return (copy.getPrice() + copy.getPrice()*0.02)*quantity;
    }
}
