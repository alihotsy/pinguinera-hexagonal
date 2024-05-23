package com.penguin.model.provider.values.copy;

import com.penguin.model.generic.ValueObject;

import java.util.Objects;

public class NumOfPages implements ValueObject<Integer> {
    private final Integer numOfPages;

    public NumOfPages(Integer numOfPages) {
        if(Objects.isNull(numOfPages)) {
            throw new IllegalArgumentException("Num of pages must not null");
        }

        if(numOfPages < 0) {
           throw new IllegalArgumentException("Num of pages must not be minus than 0");
        }
        this.numOfPages = numOfPages;
    }

    @Override
    public Integer value() {
        return numOfPages;
    }
}
