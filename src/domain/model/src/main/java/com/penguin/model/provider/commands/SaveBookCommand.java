package com.penguin.model.provider.commands;

import com.penguin.model.generic.Command;

public class SaveBookCommand extends Command {

    private String bookStoreQuoteId;
    private String bookType;
    private String title;
    private String author;
    private String areaOfKnowledge;
    private Integer numOfPages;
    private Integer copiesOfTheBook;
    private Double price;

    public SaveBookCommand() {
    }

    public String getBookStoreQuoteId() {
        return bookStoreQuoteId;
    }

    public void setBookStoreQuoteId(String bookStoreQuoteId) {
        this.bookStoreQuoteId = bookStoreQuoteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCopiesOfTheBook() {
        return copiesOfTheBook;
    }

    public void setCopiesOfTheBook(Integer copiesOfTheBook) {
        this.copiesOfTheBook = copiesOfTheBook;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getAreaOfKnowledge() {
        return areaOfKnowledge;
    }

    public void setAreaOfKnowledge(String areaOfKnowledge) {
        this.areaOfKnowledge = areaOfKnowledge;
    }

    public Integer getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(Integer numOfPages) {
        this.numOfPages = numOfPages;
    }
}
