package com.penguin.model.provider.entity;

import com.penguin.model.provider.values.customer.DateRegister;
import com.penguin.model.provider.values.customer.Email;
import com.penguin.model.provider.values.customer.Name;
import com.penguin.model.provider.values.customer.Password;
import com.penguin.model.provider.values.identities.CustomerId;
import com.penguin.model.generic.Entity;

public class Customer extends Entity<CustomerId> {

    private final Name name;
    private final Email email;
    private final Password password;
    private final DateRegister date;

    public Customer(Name name, Email email, Password password, DateRegister date) {
        super(new CustomerId());
        this.name = name;
        this.email = email;
        this.password = password;
        this.date = date;
    }

    public static Customer from(
            Name name,
            Email email,
            Password password,
            DateRegister date
    ){
        return new Customer(name, email, password, date);
    }

    public void calculateDiscount(){}

    public void calculateSeniority(){}
}
