package com.penguin.model.provider.entity;

import com.penguin.model.provider.values.copy.*;
import com.penguin.model.provider.values.identities.CopyId;
import com.penguin.model.generic.Entity;

public abstract class Copy extends Entity<CopyId> {

    private Type type;
    private final Title title;
    private final Author author;
    private final Stock stock;
    private final PublicationYear publicationYear;
    private Price price;


    public Copy(Type type,Title title, Author author, Stock stock, Price price, PublicationYear publicationYear) {
        super(new CopyId());
        this.type = type;
        this.title = title;
        this.author = author;
        this.stock = stock;
        this.price = price;
        this.publicationYear = publicationYear;
    }

    public abstract void calculateIndividualPrice();

    public void applyDiscount(){}

    public void appplyIncrementPrice(){}

    public Double getPrice() {
        return price.value();
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getTitle() {
        return title.value();
    }

    public String getAuthor() {
        return author.value();
    }

    public Integer getStock() {
        return stock.value();
    }

    public Integer getPublicationYear() {
        return publicationYear.value();
    }

    public String getType() {
        return type.value();
    }

    public void setType(Type type) {
        this.type = type;
    }


}
