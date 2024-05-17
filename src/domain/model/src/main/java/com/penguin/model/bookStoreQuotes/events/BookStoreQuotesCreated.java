package com.penguin.model.bookStoreQuotes.events;

import com.penguin.model.generic.DomainEvent;

public class BookStoreQuotesCreated extends DomainEvent {

    public BookStoreQuotesCreated() {
        super(TypeEvent.BOOK_STORE_QUOTES_CREATED.toString());
    }
}
