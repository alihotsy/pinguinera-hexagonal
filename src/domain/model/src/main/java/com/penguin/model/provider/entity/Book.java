package com.penguin.model.provider.entity;

import com.penguin.model.provider.values.copy.*;

public class Book extends Copy{

    private Book(BookType bookType, Title title, Author author, AreaOfKnowledge areaOfKnowledge,NumOfPages numOfPages ,CopiesOfTheBook copiesOfTheBook, Price price) {
        super(bookType,title, author, areaOfKnowledge,numOfPages ,copiesOfTheBook, price);
    }

    public static Book from(
            BookType bookType,
            Title title,
            Author author,
            AreaOfKnowledge areaOfKnowledge,
            NumOfPages numOfPages,
            CopiesOfTheBook copiesOfTheBook,
            Price price
    ){
        return new Book(bookType,title, author, areaOfKnowledge,numOfPages ,copiesOfTheBook, price);
    }

    @Override
    public void calculateIndividualPrice() {
        double currentPrice = this.getPrice();
        double newPrice = currentPrice + currentPrice/3;
        this.setPrice(new Price(newPrice));
    }
}
