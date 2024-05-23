package com.penguin.model.provider.events;

import com.penguin.model.generic.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class BookSaved extends DomainEvent {

    private String bookStoreQuoteId;
    private String title;
    private String author;
    private String areaOfKnowledge;
    private Integer numOfPages;
    private Integer copiesOfTheBook;
    private Double price;

    public BookSaved(String bookStoreQuoteId,String title, String author, String areaOfKnowledge,Integer numOfPages,Integer copiesOfTheBook, Double price, String type) {
        super(TypeEvent.BOOK_SAVED.toString());
        this.title = title;
        this.author = author;
        this.areaOfKnowledge = areaOfKnowledge;
        this.numOfPages = numOfPages;
        this.copiesOfTheBook = copiesOfTheBook;
        this.price = price;
        this.type = type;
        this.bookStoreQuoteId = bookStoreQuoteId;
    }

    public BookSaved() {
        super();
    }

    public BookSaved(Instant when, UUID uuid, String type, String aggregateRootId, String aggregate, Long versionType, String bookstoreQuoteId, String title, String author, String areaOfKnowledge, Integer numOfPages ,Integer copiesOfTheBook, Double price) {
        super(when, uuid, type, aggregateRootId, aggregate, versionType);
        this.bookStoreQuoteId = bookstoreQuoteId;
        this.title = title;
        this.author = author;
        this.areaOfKnowledge = areaOfKnowledge;
        this.numOfPages = numOfPages;
        this.copiesOfTheBook = copiesOfTheBook;
        this.price = price;
        this.type = type;
    }

    public String getBookStoreQuoteId() {
        return bookStoreQuoteId;
    }

    public void setBookstoreQuoteId(String bookstoreQuoteId) {
        this.bookStoreQuoteId = bookstoreQuoteId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "BookSaved{" +
                "bookStoreQuoteId='" + bookStoreQuoteId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", stock=" + copiesOfTheBook +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }
}
