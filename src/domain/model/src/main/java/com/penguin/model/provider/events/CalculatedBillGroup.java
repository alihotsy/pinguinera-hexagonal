package com.penguin.model.provider.events;

import com.penguin.model.generic.DomainEvent;
import com.penguin.model.provider.dto.Bill;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CalculatedBillGroup extends DomainEvent {

    private double finalPrice;
    private List<Bill> billGroups = new ArrayList<>();

    public CalculatedBillGroup(List<Bill> billGroups) {
        super(TypeEvent.CALCULATED_BILL_GROUP.toString());
        this.billGroups = billGroups;
    }

    public CalculatedBillGroup() {}

    public CalculatedBillGroup(Instant when, UUID uuid, String type, String aggregateRootId, String aggregate, Long versionType, double finalPrice, List<Bill> billGroups) {
        super(when, uuid, type, aggregateRootId, aggregate, versionType);
        this.finalPrice = finalPrice;
        this.billGroups = billGroups;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public List<Bill> getBillGroups() {
        return billGroups;
    }

    public void setBillGroups(List<Bill> billGroups) {
        this.billGroups = billGroups;
    }
}
