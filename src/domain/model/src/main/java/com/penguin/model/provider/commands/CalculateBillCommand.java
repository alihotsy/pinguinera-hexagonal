package com.penguin.model.provider.commands;

import com.penguin.model.generic.Command;

import java.time.LocalDate;
import java.util.List;

public class CalculateBillCommand extends Command {

    private String bookStoreQuoteId;
    private LocalDate registeredAt;
    private List<BookItemCommand> items;

    public CalculateBillCommand(String bookStoreQuoteId,LocalDate registeredAt, List<BookItemCommand> items) {
        this.bookStoreQuoteId = bookStoreQuoteId;
        this.registeredAt = registeredAt;
        this.items = items;
    }

    public List<BookItemCommand> getItems() {
        return items;
    }

    public void setItems(List<BookItemCommand> items) {
        this.items = items;
    }

    public CalculateBillCommand(LocalDate registeredAt) {
        this.registeredAt = registeredAt;
    }

    public LocalDate getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDate registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getBookStoreQuoteId() {
        return bookStoreQuoteId;
    }

    public void setBookStoreQuoteId(String bookStoreQuoteId) {
        this.bookStoreQuoteId = bookStoreQuoteId;
    }
}
