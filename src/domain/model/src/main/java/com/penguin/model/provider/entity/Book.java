package com.penguin.model.provider.entity;

import com.penguin.model.provider.values.copy.*;

public class Book extends Copy{

    private Book(Type type,Title title, Author author, Stock stock, Price price, PublicationYear publicationYear) {
        super(type,title, author, stock, price, publicationYear);
    }

    public static Book from(
            Type type,
            Title title,
            Author author,
            Stock stock,
            PublicationYear publicationYear,
            Price price
    ){
        return new Book(type,title, author, stock, price, publicationYear);
    }

    @Override
    public void calculateIndividualPrice() {
        double currentPrice = this.getPrice();
        double newPrice = currentPrice + currentPrice/3;
        this.setPrice(new Price(newPrice));
    }
}
