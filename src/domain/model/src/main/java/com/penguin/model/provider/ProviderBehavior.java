package com.penguin.model.provider;

import com.penguin.model.provider.entity.Copy;
import com.penguin.model.provider.events.BookSaved;
import com.penguin.model.provider.events.BookStoreQuotesCreated;
import com.penguin.model.provider.events.CalculatedBill;
import com.penguin.model.provider.factory.CopyFactory;
import com.penguin.model.provider.factory.CopyFactoryImpl;
import com.penguin.model.provider.values.copy.*;
import com.penguin.model.generic.EventChange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProviderBehavior extends EventChange {

    private final CopyFactory copyFactory = new CopyFactoryImpl();

    public ProviderBehavior(Provider provider) {
        apply((BookStoreQuotesCreated  event) -> {
            provider.copies = new ArrayList<>();
            provider.copyStock = new HashMap<>();
        });
        apply((BookSaved event) -> {
            System.out.println("dsafddddddddd" + event.getPrice());
            provider.copies = new ArrayList<>();
             Copy copy = copyFactory.createCopy(
                            new Type(event.getType()),
                            new Title(event.getTitle()),
                            new Author(event.getAuthor()),
                            new Stock(event.getStock()),
                            new PublicationYear(event.getPublicationYear()), new Price(event.getPrice()));
             copy.calculateIndividualPrice();
             event.setPrice(copy.getPrice());
            provider.copies.add(copy);
            provider.setResult(copy);
        });

        apply((CalculatedBill event) -> {

            provider.copyStock.forEach((copy,quantity) -> {
                event.setPrice(event.getPrice() + provider.totalPriceForAllCopiesOfOneBook(quantity,copy));
            });

            event.setPrice(provider.discountBySeniority(event.getPrice(),event.getRegisteredAt()));

        });
    }

}

