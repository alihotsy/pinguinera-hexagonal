package com.penguin.model.bookStoreQuotes.values.copy;

public enum TypeEnum {
    NOVEL,
    BOOK;
    public static TypeEnum fromString(String type) {
        for (TypeEnum typeEnum : TypeEnum.values()) {
            if (typeEnum.name().equalsIgnoreCase(type)) {
                return typeEnum;
            }
        }
        throw new IllegalArgumentException("Invalid type: " + type);
    }
}
