package com.penguin.model.provider.dto;

import java.util.List;

public class BillGroup {

    private Double finalPrice;
    private List<Bill> billGroup;

    public BillGroup(Double finalPrice, List<Bill> billGroup) {
        this.finalPrice = finalPrice;
        this.billGroup = billGroup;
    }

    public BillGroup() {
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public List<Bill> getBillGroup() {
        return billGroup;
    }

    public void setBillGroup(List<Bill> billGroup) {
        this.billGroup = billGroup;
    }
}
