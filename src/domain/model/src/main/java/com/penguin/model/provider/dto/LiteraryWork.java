package com.penguin.model.provider.dto;

import com.penguin.model.provider.values.copy.*;

public class LiteraryWork {
    private String id;
    private String bookType;
    private String title;
    private String author;
    private String areaOfKnowledge;
    private Integer numOfPages;
    private Integer copiesOfTheBook;
    private Double price;

    public LiteraryWork(String id, String bookType, String title, String author, String areaOfKnowledge, Integer numOfPages, Integer copiesOfTheBook, Double price) {
        this.id = id;
        this.bookType = bookType;
        this.title = title;
        this.author = author;
        this.areaOfKnowledge = areaOfKnowledge;
        this.numOfPages = numOfPages;
        this.copiesOfTheBook = copiesOfTheBook;
        this.price = price;
    }

    public LiteraryWork() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getCopiesOfTheBook() {
        return copiesOfTheBook;
    }

    public void setCopiesOfTheBook(Integer copiesOfTheBook) {
        this.copiesOfTheBook = copiesOfTheBook;
    }
}
