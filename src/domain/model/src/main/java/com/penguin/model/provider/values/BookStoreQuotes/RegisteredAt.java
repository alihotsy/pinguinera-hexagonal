package com.penguin.model.provider.values.BookStoreQuotes;

import com.penguin.model.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class RegisteredAt implements ValueObject<LocalDate> {

    private final LocalDate registeredAt;

    public RegisteredAt(LocalDate registeredAt) {
        if (Objects.isNull(registeredAt)) {
            throw new IllegalArgumentException("Registered date must not be null");
        }

        this.registeredAt = registeredAt;
    }

    public static RegisteredAt of(LocalDate total) {
        return new RegisteredAt(total);
    }

    @Override
    public LocalDate value() {
        return registeredAt;
    }



}
