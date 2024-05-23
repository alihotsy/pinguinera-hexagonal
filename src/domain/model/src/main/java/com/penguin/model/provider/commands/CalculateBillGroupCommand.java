package com.penguin.model.provider.commands;

import com.penguin.model.generic.Command;

import java.time.LocalDate;
import java.util.List;

public class CalculateBillGroupCommand extends Command {
    private String bookStoreQuoteId;
    private LocalDate registeredAt;
    private List<List<BookItemCommand>> group;

    public CalculateBillGroupCommand(String bookStoreQuoteId, LocalDate registeredAt, List<List<BookItemCommand>> group) {
        this.bookStoreQuoteId = bookStoreQuoteId;
        this.registeredAt = registeredAt;
        this.group = group;
    }

    public String getBookStoreQuoteId() {
        return bookStoreQuoteId;
    }

    public void setBookStoreQuoteId(String bookStoreQuoteId) {
        this.bookStoreQuoteId = bookStoreQuoteId;
    }

    public LocalDate getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDate registeredAt) {
        this.registeredAt = registeredAt;
    }

    public List<List<BookItemCommand>> getItems() {
        return group;
    }

    public void setItems(List<List<BookItemCommand>> items) {
        this.group = items;
    }


}
