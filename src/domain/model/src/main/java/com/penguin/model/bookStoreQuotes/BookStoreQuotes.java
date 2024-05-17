package com.penguin.model.bookStoreQuotes;

import com.penguin.model.bookStoreQuotes.entity.Copy;
import com.penguin.model.bookStoreQuotes.entity.Quote;
import com.penguin.model.bookStoreQuotes.events.BookSaved;
import com.penguin.model.bookStoreQuotes.values.BookStoreQuotes.CustomerRegistrationDate;
import com.penguin.model.bookStoreQuotes.values.BookStoreQuotes.TotalDiscount;
import com.penguin.model.bookStoreQuotes.values.BookStoreQuotes.TotalIncrement;
import com.penguin.model.bookStoreQuotes.values.BookStoreQuotes.TotalPrice;
import com.penguin.model.bookStoreQuotes.values.copy.*;
import com.penguin.model.bookStoreQuotes.values.identities.BookStoreQuoteId;
import com.penguin.model.generic.AggregateRoot;
import com.penguin.model.generic.DomainEvent;

import java.util.List;

public class BookStoreQuotes extends AggregateRoot<BookStoreQuoteId> {

    protected Copy copy;
    protected TotalPrice totalPrice;
    protected TotalDiscount totalDiscount;
    protected TotalIncrement totalIncrement;
    protected CustomerRegistrationDate customerRegistrationDate;
    protected List<Copy> copies;
    protected List<Quote> quotes;

    private BookStoreQuotes(BookStoreQuoteId bookStoreQuoteId) {
        super(bookStoreQuoteId);
    }

    public BookStoreQuotes() {
        super(new BookStoreQuoteId());
        subscribe(new BookStoreQuotesEventChange(this));

    }

    public void addCopy(Title title,
                        Author author,
                        Stock stock,
                        PublicationYear publicationYear,
                        Price price,
                        Type type) {
        appendChange(new BookSaved(
                title.value(),
                author.value(),
                stock.value(),
                publicationYear.value(),
                price.value(), type.value())).apply();
    }



    public static BookStoreQuotes from(BookStoreQuoteId bookStoreQuoteId, List<DomainEvent> events) {
        var bookStoreQuotes = new BookStoreQuotes(bookStoreQuoteId);
        events.forEach(bookStoreQuotes::applyEvent);
        return bookStoreQuotes;
    }
}
