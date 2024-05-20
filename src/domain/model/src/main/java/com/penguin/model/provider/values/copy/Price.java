package com.penguin.model.provider.values.copy;

import com.penguin.model.generic.ValueObject;

import java.util.Objects;

public class Price implements ValueObject<Double> {

    private final Double price;

    public Price(Double price) {
        if(Objects.isNull(price)) {
            throw new IllegalArgumentException("Price must not be null");
        }
        if(price <= 0) {
            throw new IllegalArgumentException("Price must not contain a negative quantity");
        }
        this.price = price;
    }

    @Override
    public Double value() {
        return this.price;
    }
}
