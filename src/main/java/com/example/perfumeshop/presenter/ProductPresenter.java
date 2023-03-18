package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.model.persistence.ProductPersistence;

import java.util.List;
import java.util.stream.Collectors;

public class ProductPresenter implements IProductPresenter {
    ProductPersistence productPersistence = new ProductPersistence();
    @Override
    public List<Product> getProducts() {
        return productPersistence.findAll();
    }

    public List<Product> filterProducts(String name, String brand, boolean availability, float price) {
        List<Product> products = getProducts();
        return products.stream()
                .filter(it -> name.equals("") || it.getName().contains(name))
                .filter(it -> brand.equals("") || it.getBrand().contains(brand))
                .filter(it -> !availability || it.getAvailability())
                .filter(it -> price == -1 || it.getPrice() <= price)
                .collect(Collectors.toList());
    }
}
