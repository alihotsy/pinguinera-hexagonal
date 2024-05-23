package com.penguin.model.provider.commands;

import com.penguin.model.generic.Command;

import java.time.LocalDate;
import java.util.List;

public class CalculateMaxWholeDiscountCommand extends Command {
    private String bookStoreQuoteId;
    private LocalDate registeredAt;
    private Double budget;
    private List<BookIdCommand> books;

    public CalculateMaxWholeDiscountCommand(String bookStoreQuoteId, LocalDate registeredAt, Double budget, List<BookIdCommand> books) {
        this.bookStoreQuoteId = bookStoreQuoteId;
        this.registeredAt = registeredAt;
        this.budget = budget;
        this.books = books;
    }

    public CalculateMaxWholeDiscountCommand() {
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

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public List<BookIdCommand> getBooks() {
        return books;
    }

    public void setBooks(List<BookIdCommand> books) {
        this.books = books;
    }
}
