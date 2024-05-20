package com.penguin.model.provider.values.copy;

import com.penguin.model.generic.ValueObject;

import java.util.Objects;


public class PublicationYear implements ValueObject<Integer> {

    private final Integer publicationYear;

    public PublicationYear(Integer publicationYear) {
        if(Objects.isNull(publicationYear)) {
            throw new IllegalArgumentException("Publication year must not be null");
        }

        if(publicationYear <= 0) {
            throw new IllegalArgumentException("Invalid year");
        }
        this.publicationYear = publicationYear;
    }

    @Override
    public Integer value() {
        return this.publicationYear;
    }
}
