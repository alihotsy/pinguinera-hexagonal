package com.penguin.model.provider.values.copy;

import com.penguin.model.generic.ValueObject;

import java.util.Objects;

public class AreaOfKnowledge implements ValueObject<String> {
    private final String areaOfKnowledge;

    public AreaOfKnowledge(String areaOfKnowledge) {
        if(Objects.isNull(areaOfKnowledge)) {
            throw new IllegalArgumentException("Area of knowledge must not be null");
        }

        if(areaOfKnowledge.trim().length() > 100) {
            throw new IllegalArgumentException("Knowledge must not be greater than 100");
        }
        this.areaOfKnowledge = areaOfKnowledge;
    }

    @Override
    public String value() {
        return areaOfKnowledge;
    }
}
