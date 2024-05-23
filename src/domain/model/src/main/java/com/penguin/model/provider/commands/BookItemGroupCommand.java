package com.penguin.model.provider.commands;

import com.penguin.model.generic.Command;

import java.util.List;

public class BookItemGroupCommand extends Command {

    List<BookItemCommand> items;

    public BookItemGroupCommand(List<BookItemCommand> items) {
        this.items = items;
    }

    public List<BookItemCommand> getItems() {
        return items;
    }

    public void setItems(List<BookItemCommand> items) {
        this.items = items;
    }
}
