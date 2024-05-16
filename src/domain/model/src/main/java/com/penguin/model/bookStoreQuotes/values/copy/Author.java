package com.penguin.model.bookStoreQuotes.values.copy;

import com.penguin.model.generic.ValueObject;

public class Author implements ValueObject<String> {

    private final String author;

    public Author(String author) {
        this.author = author;
    }

    @Override
    public String value() {
        return author;
    }
}
