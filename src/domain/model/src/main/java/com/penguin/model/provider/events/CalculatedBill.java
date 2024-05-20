package com.penguin.model.provider.events;

import com.penguin.model.provider.dto.LiteraryWork;
import com.penguin.model.provider.entity.Copy;
import com.penguin.model.generic.DomainEvent;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CalculatedBill extends DomainEvent {

    private Double price = 0.0;
    private List<LiteraryWork> copies;

    private LocalDate registeredAt;

    public CalculatedBill(Map<LiteraryWork, Integer> copyStock, LocalDate registeredAt) {
        super(TypeEvent.CALCULATED_BILL.toString());
        this.copies = new ArrayList<>(copyStock.keySet());
        this.registeredAt = registeredAt;
    }

    public CalculatedBill() {

    }

    public CalculatedBill(Instant when, UUID uuid, String type, String aggregateRootId, String aggregate, Long versionType, Double price, List<LiteraryWork> copies, LocalDate registeredAt) {
        super(when, uuid, type, aggregateRootId, aggregate, versionType);
        this.price = price;
        this.copies = copies;
        this.registeredAt = registeredAt;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<LiteraryWork> getCopies() {
        return copies;
    }

    public void setCopies(List<LiteraryWork> copies) {
        this.copies = copies;
    }

    public LocalDate getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDate registeredAt) {
        this.registeredAt = registeredAt;
    }
}
