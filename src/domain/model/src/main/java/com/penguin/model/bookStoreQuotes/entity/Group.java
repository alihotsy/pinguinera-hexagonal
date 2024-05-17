package com.penguin.model.bookStoreQuotes.entity;

import com.penguin.model.bookStoreQuotes.values.BookStoreQuotes.TotalPrice;
import com.penguin.model.bookStoreQuotes.values.identities.GroupId;
import com.penguin.model.bookStoreQuotes.values.shared.DiscountApply;
import com.penguin.model.generic.Entity;

import java.util.List;

public class Group extends Entity<GroupId> {

    private TotalPrice totalPrice;
    private List<Groupment> groupments;
    private DiscountApply discountApply;

    public Group(GroupId id) {
        super(id);
    }
}
