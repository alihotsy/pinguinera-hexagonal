package com.penguin.model.provider.factory;

import com.penguin.model.provider.entity.Book;
import com.penguin.model.provider.entity.Copy;
import com.penguin.model.provider.entity.Novel;
import com.penguin.model.provider.values.copy.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CopyFactoryImpl implements CopyFactory{
    @Override
    public Copy createCopy(Type type, Title title, Author author, Stock stock, PublicationYear publicationYear, Price price) {
        Map<String, Copy> bookTypeMap = new HashMap<>();
        bookTypeMap.put("Book",  Book.from(type,title, author, stock, publicationYear, price));
        bookTypeMap.put("Novel", Novel.from(type,title, author, stock, publicationYear, price));
        return bookTypeMap.get(type.value());
    }

}
