package com.penguin.model.provider.dto;

import java.time.LocalDate;
import java.util.List;

public class Bill {

    private LocalDate registeredAt;
    private List<LiteraryWork> copies;
    private double price;

    public Bill() {
    }

    public Bill(LocalDate registeredAt, List<LiteraryWork> copies, double price) {
        this.registeredAt = registeredAt;
        this.copies = copies;
        this.price = price;
    }

    public LocalDate getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDate registeredAt) {
        this.registeredAt = registeredAt;
    }

    public List<LiteraryWork> getCopies() {
        return copies;
    }

    public void setCopies(List<LiteraryWork> copies) {
        this.copies = copies;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
