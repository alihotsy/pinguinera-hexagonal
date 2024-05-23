package com.penguin.model.provider;

import com.penguin.model.provider.dto.Bill;
import com.penguin.model.provider.entity.Copy;
import com.penguin.model.provider.events.*;
import com.penguin.model.provider.factory.CopyFactory;
import com.penguin.model.provider.factory.CopyFactoryImpl;
import com.penguin.model.provider.values.copy.*;
import com.penguin.model.generic.EventChange;

import java.util.ArrayList;
import java.util.HashMap;

public class ProviderBehavior extends EventChange {

    private final CopyFactory copyFactory = new CopyFactoryImpl();

    public ProviderBehavior(Provider provider) {
        apply((BookStoreQuotesCreated  event) -> {
            provider.copies = new ArrayList<>();
            provider.copyStock = new HashMap<>();
        });
        apply((BookSaved event) -> {
            provider.copies = new ArrayList<>();
             Copy copy = copyFactory.createCopy(
                            new BookType(event.getType()),
                            new Title(event.getTitle()),
                            new Author(event.getAuthor()),
                            new AreaOfKnowledge(event.getAreaOfKnowledge()),
                            new NumOfPages(event.getNumOfPages()),
                            new CopiesOfTheBook(event.getCopiesOfTheBook()),
                            new Price(event.getPrice()));
             copy.calculateIndividualPrice();
             event.setPrice(copy.getPrice());
            provider.copies.add(copy);
            provider.setResult(copy);
        });

        apply((CalculatedBill event) -> {

            provider.copyStock.forEach((copy,quantity) -> {
                event.setPrice(event.getPrice() + provider.totalPriceForAllCopiesOfOneBook(quantity,copy));
            });

            event.setPrice(provider.discountBySeniority(event.getPrice(),event.getRegisteredAt()));

        });

        apply((CalculatedMaxWholeDiscount event) -> {

            event.getBooks()
                    .stream()
                    .peek(book -> {
                        book.setCopiesOfTheBook(0);
                        double budget = event.getBudget();
                        int quantity = 0;
                        double maxDiscount = 0.0;
                        boolean canEnoughMoney = true;
                        while (canEnoughMoney) {
                            quantity++;

                            if (quantity <= 10) {
                                double detalPrice = book.getPrice() + book.getPrice() * 0.02;
                                budget -= detalPrice;
                            } else {
                                final int COPIES_EXCLUDED = 10;
                                int copiesForDiscount = quantity - COPIES_EXCLUDED;
                                double totalDiscount = copiesForDiscount * 0.0015;

                                if (totalDiscount <= 1) {
                                    double wholeSaleDiscount = copiesForDiscount * (book.getPrice() - book.getPrice() * totalDiscount);
                                    System.out.println(wholeSaleDiscount + " wholesale" + " budget: "+budget+ "maxDisc "+maxDiscount);
                                    if (wholeSaleDiscount >= maxDiscount && wholeSaleDiscount <= budget) {
                                        maxDiscount = wholeSaleDiscount;
                                    } else if(wholeSaleDiscount < maxDiscount && wholeSaleDiscount <= budget) {
                                        budget -= maxDiscount;
                                        maxDiscount = 0.0;
                                    }else {
                                        quantity--;

                                        budget -= maxDiscount;
                                        canEnoughMoney = false;
                                    }
                                } else {
                                    System.out.println("Break?");
                                    break;
                                }

                            }
                            if (budget >= 0) {
                                event.setBudget(budget);
                                book.setCopiesOfTheBook(quantity);
                            }
                        }

                    }).toList();

            int totalCopies = provider.calculateTotalCopies(event.getBooks());

            event.setTotalCopies(totalCopies);


        });

        apply((CalculatedBillGroup event) -> {
            double finalPrice = event.getBillGroups()
                    .stream()
                    .map(Bill::getPrice)
                    .reduce(0.0, Double::sum);
            event.setFinalPrice(finalPrice);
        });
    }

}

