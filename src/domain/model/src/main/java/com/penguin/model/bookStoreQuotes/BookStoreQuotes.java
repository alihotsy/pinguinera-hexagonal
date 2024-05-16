package com.penguin.model.bookStoreQuotes;

import com.penguin.model.bookStoreQuotes.entity.Copy;
import com.penguin.model.bookStoreQuotes.entity.Quote;
import com.penguin.model.bookStoreQuotes.events.BookSaved;
import com.penguin.model.bookStoreQuotes.values.copy.*;
import com.penguin.model.bookStoreQuotes.values.identities.BookStoreQuoteId;
import com.penguin.model.generic.AggregateRoot;
import com.penguin.model.generic.DomainEvent;

import java.util.List;

public class BookStoreQuotes extends AggregateRoot<BookStoreQuoteId> {

    protected Copy copy;
    protected List<Copy> copies;
    protected List<Quote> quotes;

    public BookStoreQuotes(BookStoreQuoteId bookStoreQuoteId) {
        super(bookStoreQuoteId);
    }

    public BookStoreQuotes(Title title,
                           Author author,
                           Stock stock,
                           PublicationYear publicationYear,
                           Price price,
                           Type type
    ) {
        super(new BookStoreQuoteId());
        subscribe(new BookStoreQuotesEventChange(this));
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
