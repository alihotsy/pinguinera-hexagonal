package com.penguin.model.provider.entity;

import com.penguin.model.provider.values.copy.*;

public class Novel extends Copy{


    private Novel(Type type,Title title, Author author, Stock stock, Price price, PublicationYear publicationYear) {
        super(type,title, author, stock, price, publicationYear);
    }

    public static Novel from(
            Type type,
            Title title,
            Author author,
            Stock stock,
            PublicationYear publicationYear,
            Price price
    ){
        return new Novel(type,title, author, stock, price, publicationYear);
    }

    @Override
    public void calculateIndividualPrice() {
        double currentPrice = this.getPrice();
        this.setPrice(new Price(currentPrice * 2));
    }
}

