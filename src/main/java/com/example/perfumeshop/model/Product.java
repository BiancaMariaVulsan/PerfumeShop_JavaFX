package com.example.perfumeshop.model;

import javafx.beans.property.*;

public class Product {
        private final IntegerProperty id = new SimpleIntegerProperty();
        private final StringProperty name = new SimpleStringProperty();
        private final IntegerProperty quantity = new SimpleIntegerProperty(); //availability
        private final DoubleProperty price = new SimpleDoubleProperty();

        public Product(int id, String name, int quantity, double price) {
            this.id.set(id);
            this.name.set(name);
            this.quantity.set(quantity);
            this.price.set(price);
        }

        public Product(String name, int quantity, double price) {
            this.name.set(name);
            this.quantity.set(quantity);
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

        public int getQuantity() {
            return quantity.get();
        }

        public IntegerProperty quantityProperty() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity.set(quantity);
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
