package com.penguin.model.provider.commands;

import com.penguin.model.generic.Command;

public class GetAllBooksCommand extends Command {

    private String bookStoreQuoteId;
    public GetAllBooksCommand() {
    }

    public GetAllBooksCommand(String bookStoreQuoteId) {
        this.bookStoreQuoteId = bookStoreQuoteId;
    }

    public String getBookStoreQuoteId() {
        return bookStoreQuoteId;
    }

    public void setBookStoreQuoteId(String bookStoreQuoteId) {
        this.bookStoreQuoteId = bookStoreQuoteId;
    }
}
