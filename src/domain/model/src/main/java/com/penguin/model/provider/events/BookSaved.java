package com.penguin.model.provider.events;

import com.penguin.model.generic.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class BookSaved extends DomainEvent {

    private String bookStoreQuoteId;
    private String title;
    private String author;
    private Integer stock;
    private Integer publicationYear;
    private Double price;
    private String type;

    public BookSaved(String bookStoreQuoteId,String title, String author, Integer stock, Integer publicationYear, Double price, String type) {
        super(TypeEvent.BOOK_SAVED.toString());
        this.title = title;
        this.author = author;
        this.stock = stock;
        this.publicationYear = publicationYear;
        this.price = price;
        this.type = type;
        this.bookStoreQuoteId = bookStoreQuoteId;
    }

    public BookSaved() {
        super();
    }

    public BookSaved(Instant when, UUID uuid, String type, String aggregateRootId, String aggregate, Long versionType, String bookstoreQuoteId, String title, String author, Integer stock, Integer publicationYear, Double price) {
        super(when, uuid, type, aggregateRootId, aggregate, versionType);
        this.bookStoreQuoteId = bookstoreQuoteId;
        this.title = title;
        this.author = author;
        this.stock = stock;
        this.publicationYear = publicationYear;
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

    @Override
    public String toString() {
        return "BookSaved{" +
                "bookStoreQuoteId='" + bookStoreQuoteId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", stock=" + stock +
                ", publicationYear=" + publicationYear +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }
}
