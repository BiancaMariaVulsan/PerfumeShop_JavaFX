package com.example.perfumeshop.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private final IntegerProperty id =  new SimpleIntegerProperty();
    private final StringProperty firstName =  new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty cnp = new SimpleStringProperty();

    public Person(int id, String firstName, String lastName, String cnp) {
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.cnp.set(cnp);
    }

    public Person(String firstName, String lastName, String cnp) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.cnp.set(cnp);
    }

    public Person() {}

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getCnp() {
        return cnp.get();
    }

    public StringProperty cnpProperty() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp.set(cnp);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName.get() + '\'' +
                ", lastName='" + lastName.get() + '\'' +
                ", cnp='" + cnp.get() + '\'' +
                '}';
    }
}
