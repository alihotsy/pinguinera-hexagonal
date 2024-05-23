package com.penguin.model.provider.events;

import com.penguin.model.generic.DomainEvent;
import com.penguin.model.provider.dto.LiteraryWork;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CalculatedMaxWholeDiscount extends DomainEvent {

    private LocalDate registeredAt;
    private double budget;
    private List<LiteraryWork> books;
    private Integer totalCopies = 0;

    public CalculatedMaxWholeDiscount() {
    }

    public CalculatedMaxWholeDiscount(double budget, List<LiteraryWork> books, LocalDate registeredAt) {
        super(TypeEvent.CALCULATED_MAX_DISCOUNT.toString());
        this.budget = budget;
        this.books = books;
        this.registeredAt = registeredAt;
    }

    public CalculatedMaxWholeDiscount(Instant when, UUID uuid, String type, String aggregateRootId, String aggregate, Long versionType, double budget, List<LiteraryWork> books) {
        super(when, uuid, type, aggregateRootId, aggregate, versionType);
        this.budget = budget;
        this.books = books;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<LiteraryWork> getBooks() {
        return books;
    }

    public void setBooks(List<LiteraryWork> books) {
        this.books = books;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public LocalDate getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDate registeredAt) {
        this.registeredAt = registeredAt;
    }
}
