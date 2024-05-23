package com.penguin.model.provider.entity;

import com.penguin.model.provider.values.copy.*;

public class Novel extends Copy{


    private Novel(BookType bookType, Title title, Author author, AreaOfKnowledge areaOfKnowledge,NumOfPages numOfPages ,CopiesOfTheBook copiesOfTheBook, Price price) {
        super(bookType,title, author, areaOfKnowledge,numOfPages ,copiesOfTheBook, price);
    }

    public static Novel from(
            BookType bookType,
            Title title,
            Author author,
            AreaOfKnowledge areaOfKnowledge,
            NumOfPages numOfPages ,
            CopiesOfTheBook copiesOfTheBook,
            Price price
    ){
        return new Novel(bookType,title, author, areaOfKnowledge,numOfPages ,copiesOfTheBook, price);
    }

    @Override
    public void calculateIndividualPrice() {
        double currentPrice = this.getPrice();
        this.setPrice(new Price(currentPrice * 2));
    }
}

