package com.example.perfumeshop.model;

import javafx.beans.property.*;

public class Product {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty brand = new SimpleStringProperty();
    private final BooleanProperty availability = new SimpleBooleanProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();

    public Product(int id, String name, String brand, boolean availability, double price) {
        this.id.set(id);
        this.name.set(name);
        this.brand.set(brand);
        this.availability.set(availability);
        this.price.set(price);
    }

    public Product(String name, String brand, boolean availability, double price) {
        this.name.set(name);
        this.brand.set(brand);
        this.availability.set(availability);
        this.price.set(price);
    }

    public Product() {}

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getBrand() {
        return brand.get();
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public boolean getAvailability() {
        return availability.get();
    }

    public BooleanProperty availabilityProperty() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability.set(availability);
    }


    @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name=" + name +
                    ", price=" + price +
                    '}';
        }
}
