package com.example.perfumeshop.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Employee extends Person {
    public void setShopId(int shopId) {
        this.shopId.set(shopId);
    }

    private final IntegerProperty shopId = new SimpleIntegerProperty();;

    public Employee(String firstName, String lastName, String username, String password, int shopId) {
        super(firstName, lastName, username, password);
        this.shopId.set(shopId);
    }
    public int getShopId() {
        return shopId.get();
    }

    public IntegerProperty shopIdProperty() {
        return shopId;
    }
}
