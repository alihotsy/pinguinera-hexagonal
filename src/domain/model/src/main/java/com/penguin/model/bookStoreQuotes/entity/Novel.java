package com.penguin.model.bookStoreQuotes.entity;

import com.penguin.model.bookStoreQuotes.values.copy.*;

public class Novel extends Copy{


    public Novel(Title title, Author author, Stock stock, Price price, PublicationYear publicationYear) {
        super(title, author, stock, price, publicationYear);
    }

    public static Novel from(
            Title title,
            Author author,
            Stock stock,
            PublicationYear publicationYear,
            Price price
    ){
        return new Novel(title, author, stock, price, publicationYear);
    }

    @Override
    public void calculateIndividualPrice() {
        double currentPrice = this.getPrice().value();
        this.setPrice(new Price(currentPrice * 2));
    }
}

