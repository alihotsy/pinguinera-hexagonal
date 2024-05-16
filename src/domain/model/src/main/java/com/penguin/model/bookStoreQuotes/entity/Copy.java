package com.penguin.model.bookStoreQuotes.entity;

import com.penguin.model.bookStoreQuotes.values.copy.*;
import com.penguin.model.bookStoreQuotes.values.identities.CopyId;
import com.penguin.model.generic.Entity;

public abstract class Copy extends Entity<CopyId> {

    private final Title title;
    private final Author author;
    private final Stock stock;
    private final PublicationYear publicationYear;
    private Price price;


    public Copy(Title title, Author author, Stock stock, Price price, PublicationYear publicationYear) {
        super(new CopyId());
        this.title = title;
        this.author = author;
        this.stock = stock;
        this.price = price;
        this.publicationYear = publicationYear;
    }

    public abstract void calculateIndividualPrice();

    public void applyDiscount(){}

    public void appplyIncrementPrice(){}

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
