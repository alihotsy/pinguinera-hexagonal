package com.penguin.model.provider.commands;

import com.penguin.model.generic.Command;

public class BookIdCommand extends Command {
    private String bookId;

    public BookIdCommand(String bookId) {
        this.bookId = bookId;
    }

    public BookIdCommand() {
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
