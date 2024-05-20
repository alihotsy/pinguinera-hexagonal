package com.penguin.model.provider.dto;

public class LiteraryWork {
    private String id;
    private String type;
    private String title;
    private String author;
    private Integer copies;
    private Integer yearOfPublication;
    private Double price;

    public LiteraryWork(String id, String type, String title, String author, Integer copies, Integer yearOfPublication, Double price) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.yearOfPublication = yearOfPublication;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
