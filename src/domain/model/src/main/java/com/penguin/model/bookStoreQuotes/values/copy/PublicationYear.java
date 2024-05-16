package com.penguin.model.bookStoreQuotes.values.copy;

import com.penguin.model.generic.ValueObject;


public class PublicationYear implements ValueObject<Integer> {

    private final Integer publicationYear;

    public PublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public Integer value() {
        return this.publicationYear;
    }
}
