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
    public Copy createCopy(BookType bookType, Title title, Author author, AreaOfKnowledge areaOfKnowledge,NumOfPages numOfPages ,CopiesOfTheBook copiesOfTheBook, Price price) {
        Map<String, Copy> bookTypeMap = new HashMap<>();
        bookTypeMap.put("Book",  Book.from(bookType,title, author, areaOfKnowledge, numOfPages, copiesOfTheBook,price));
        bookTypeMap.put("Novel", Novel.from(bookType,title, author, areaOfKnowledge, numOfPages, copiesOfTheBook,price));
        return bookTypeMap.get(bookType.value());
    }

}
