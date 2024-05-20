package com.penguin.model.provider.values.copy;

import com.penguin.model.generic.ValueObject;

import java.util.Objects;

public class Title implements ValueObject<String>{

    private final String title;

    public Title(String title) {
        if(Objects.isNull(title)) {
            throw new IllegalArgumentException("Title must not be null");
        }
        if(title.trim().length() <= 2) {
            throw new IllegalArgumentException("Title must have a length greater than 2 characters");
        }
        this.title = title;
    }

    @Override
    public String value() {
        return this.title;
    }
}
