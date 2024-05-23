package com.penguin.model.provider.dto;

import java.util.List;

public class MaxWholeDiscount {

    private Double budget;
    private List<LiteraryWork> books;
    private Integer totalCopies;

    public MaxWholeDiscount(Double budget, List<LiteraryWork> books, Integer totalCopies) {
        this.budget = budget;
        this.books = books;
        this.totalCopies = totalCopies;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
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
}
