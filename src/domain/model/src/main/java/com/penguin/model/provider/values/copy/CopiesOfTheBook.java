package com.penguin.model.provider.values.copy;

import com.penguin.model.generic.ValueObject;

import java.util.Objects;

public class CopiesOfTheBook implements ValueObject<Integer> {

    private final Integer stock;

    public CopiesOfTheBook(Integer stock) {
        if(Objects.isNull(stock)) {
            throw new IllegalArgumentException("Copies must not be null");
        }

        if(stock <= 0) {
            throw new IllegalArgumentException("Copies must be more than 0");
        }
        this.stock = stock;
    }

    @Override
    public Integer value() {
        return this.stock;
    }
}
