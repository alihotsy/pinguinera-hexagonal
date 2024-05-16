package com.penguin.model.bookStoreQuotes.entity;

import com.penguin.model.bookStoreQuotes.values.identities.CustomerId;
import com.penguin.model.bookStoreQuotes.values.identities.QuoteId;
import com.penguin.model.bookStoreQuotes.values.quote.Discount;
import com.penguin.model.bookStoreQuotes.values.quote.Total;
import com.penguin.model.generic.Entity;

import java.util.List;

public class Quote extends Entity<QuoteId> {

    private final CustomerId customerId;
    private final List<Copy> copies;
    private final Total total;
    private final Discount discount;

    public Quote(CustomerId customerId, List<Copy> copies, String author, Total total, Discount discount) {
        super(new QuoteId());
        this.customerId = customerId;
        this.copies = copies;
        this.total = total;
        this.discount = discount;
    }

    public static Quote from(
            CustomerId customerId,
            List<Copy> copies,
            String author,
            Total total,
            Discount discount
    ){
        return new Quote(customerId, copies, author, total, discount);
    }

    public void applyWholeSalePurchaseIncrement(){}

    public void applyRetailSalePurchaseIncrement(){}

    public void appyDiscount(){}

}
