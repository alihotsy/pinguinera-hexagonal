package com.penguin.model.provider.values.copy;

import com.penguin.model.generic.ValueObject;

public class BookType implements ValueObject<String> {

    private final String type;

    public BookType(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        this.type = type;
    }


    @Override
    public String value() {
        return this.type;
    }
}

