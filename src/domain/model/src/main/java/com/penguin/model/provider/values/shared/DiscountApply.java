package com.penguin.model.provider.values.shared;

import com.penguin.model.generic.ValueObject;

public class DiscountApply implements ValueObject<Double> {

    private final Double discount;

    public DiscountApply(Double discount) {
        if (discount == null || discount < 0) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.discount = discount;
    }

    public static DiscountApply of(Double discount){
        return new DiscountApply(discount);
    }

    @Override
    public Double value() {
        return discount;
    }
}
