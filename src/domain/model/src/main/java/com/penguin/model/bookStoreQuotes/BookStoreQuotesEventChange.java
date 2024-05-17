package com.penguin.model.bookStoreQuotes;

import com.penguin.model.bookStoreQuotes.entity.Book;
import com.penguin.model.bookStoreQuotes.entity.Novel;
import com.penguin.model.bookStoreQuotes.events.BookSaved;
import com.penguin.model.bookStoreQuotes.events.BookStoreQuotesCreated;
import com.penguin.model.bookStoreQuotes.values.copy.*;
import com.penguin.model.generic.EventChange;

import java.util.ArrayList;

public class BookStoreQuotesEventChange extends EventChange {



    public BookStoreQuotesEventChange(BookStoreQuotes bookStoreQuotes) {
        apply((BookStoreQuotesCreated  event) -> {
            bookStoreQuotes.copies = new ArrayList<>();
            bookStoreQuotes.quotes = new ArrayList<>();
        });
        apply((BookSaved event) ->{
            if("Book".equals(event.getType())){
                Book book = Book.from(
                        new Title(event.getTitle()),
                        new Author(event.getAuthor()),
                        new Stock(event.getStock()),
                        new PublicationYear(event.getPublicationYear()),
                        new Price(event.getPrice())
                );
                book.calculateIndividualPrice();
                event.setPrice(book.getPrice().value());
                bookStoreQuotes.copy = book;
            }else if("Novel".equals(event.getType())){
                Novel novel = Novel.from(
                        new Title(event.getTitle()),
                        new Author(event.getAuthor()),
                        new Stock(event.getStock()),
                        new PublicationYear(event.getPublicationYear()),
                        new Price(event.getPrice())
                );
                novel.calculateIndividualPrice();
                event.setPrice(novel.getPrice().value());
                bookStoreQuotes.copy = novel;
            }else throw new IllegalArgumentException("Invalid type");
        });
    }

}

