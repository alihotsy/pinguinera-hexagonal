package com.penguin.model.bookStoreQuotes;

import com.penguin.model.bookStoreQuotes.entity.Book;
import com.penguin.model.bookStoreQuotes.entity.Novel;
import com.penguin.model.bookStoreQuotes.events.BookSaved;
import com.penguin.model.bookStoreQuotes.values.copy.*;
import com.penguin.model.generic.EventChange;

public class BookStoreQuotesEventChange extends EventChange {

    public BookStoreQuotesEventChange(BookStoreQuotes bookStoreQuotes) {
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
                System.out.println("BOOK: " + book.getPrice().value());
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
                bookStoreQuotes.copy = novel;
            }else throw new IllegalArgumentException("Invalid type");
        });
    }
}
