package com.penguin.model.bookStoreQuotes.events;

import com.penguin.model.generic.DomainEvent;

public class BookSaved extends DomainEvent {

    private String title;
    private String author;
    private Integer stock;
    private Integer publicationYear;
    private Double price;
    private String type;

    public BookSaved(String title, String author, Integer stock, Integer publicationYear, Double price, String type) {
        super(TypeEvent.BOOK_SAVED.toString());
        this.title = title;
        this.author = author;
        this.stock = stock;
        this.publicationYear = publicationYear;
        this.price = price;
        this.type = type;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
