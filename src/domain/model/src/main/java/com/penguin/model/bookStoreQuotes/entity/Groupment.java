package com.penguin.model.bookStoreQuotes.entity;

import com.penguin.model.bookStoreQuotes.values.BookStoreQuotes.TotalPrice;
import com.penguin.model.bookStoreQuotes.values.ProductId;
import com.penguin.model.bookStoreQuotes.values.identities.GroupmenId;
import com.penguin.model.bookStoreQuotes.values.shared.DiscountApply;
import com.penguin.model.generic.Entity;

import java.util.List;

public class Groupment extends Entity<GroupmenId> {

    private List<ProductId> productIds;
    private TotalPrice totalPrice;
    private DiscountApply discountApply;

    public Groupment(GroupmenId id) {
        super(id);
    }

    private Groupment(List<ProductId> productIds, TotalPrice totalPrice, DiscountApply discountApply) {
        super(new GroupmenId());
        this.productIds = productIds;
        this.totalPrice = totalPrice;
        this.discountApply = discountApply;
    }
}
