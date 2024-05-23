package com.penguin.model.provider.entity;

import com.penguin.model.provider.values.copy.*;
import com.penguin.model.provider.values.identities.CopyId;
import com.penguin.model.generic.Entity;

public abstract class Copy extends Entity<CopyId> {

    private BookType bookType;
    private Title title;
    private Author author;
    private AreaOfKnowledge areaOfKnowledge;
    private NumOfPages numOfPages;
    private CopiesOfTheBook copiesOfTheBook;
    private Price price;


    public Copy(BookType bookType, Title title, Author author, AreaOfKnowledge areaOfKnowledge, NumOfPages numOfPages, CopiesOfTheBook copiesOfTheBook, Price price) {
        super(new CopyId());
        this.bookType = bookType;
        this.title = title;
        this.author = author;
        this.areaOfKnowledge = areaOfKnowledge;
        this.numOfPages = numOfPages;
        this.copiesOfTheBook = copiesOfTheBook;
        this.price = price;
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


    public String getAreaOfKnowledge() {
        return areaOfKnowledge.value();
    }


    public String getType() {
        return bookType.value();
    }

    public Integer getNumOfPages() {
        return numOfPages.value();
    }

    public Integer getCopiesOfTheBook() {
        return copiesOfTheBook.value();
    }

    public void setType(BookType bookType) {
        this.bookType = bookType;
    }

    public void setAreaOfKnowledge(AreaOfKnowledge areaOfKnowledge) {
        this.areaOfKnowledge = areaOfKnowledge;
    }

    public void setNumOfPages(NumOfPages numOfPages) {
        this.numOfPages = numOfPages;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }
}
