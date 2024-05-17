package com.penguin.model.bookStoreQuotes.values.BookStoreQuotes;

import com.penguin.model.generic.ValueObject;

public class TotalIncrement implements ValueObject<Double> {

    private final Double totalIncrement;

    public TotalIncrement(Double total) {
        if (total == null) {
            throw new IllegalArgumentException("Total cannot be null");
        }
        if (total < 0) {
            throw new IllegalArgumentException("Total cannot be negative");
        }
        this.totalIncrement = total;
    }

    public static TotalIncrement of(Double total) {
        return new TotalIncrement(total);
    }

    @Override
    public Double value() {
        return totalIncrement;
    }

}
