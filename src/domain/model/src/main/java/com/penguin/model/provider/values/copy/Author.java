package com.penguin.model.provider.values.copy;

import com.penguin.model.generic.ValueObject;

import java.util.Objects;

public class Author implements ValueObject<String> {

    private final String author;

    public Author(String author) {
        if(Objects.isNull(author)) {
            throw new IllegalArgumentException("Author must not be null");
        }

        if(author.trim().length() <= 2) {
            throw new IllegalArgumentException("Title must have a length greater than 2 characters");
        }
        this.author = author;
    }

    @Override
    public String value() {
        return author;
    }
}
