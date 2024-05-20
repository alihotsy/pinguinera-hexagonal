package com.penguin.model.provider.commands;

import com.penguin.model.generic.Command;

public class BookItemCommand extends Command {

    private String bookId;
    private Integer quantity;

    public BookItemCommand(String bookId, Integer quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
