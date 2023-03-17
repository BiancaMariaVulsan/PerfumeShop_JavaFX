package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.model.persistence.ProductPersistence;

import java.util.List;

public class ProductPresenter implements IProductPresenter {
    ProductPersistence productPersistence = new ProductPersistence();
    @Override
    public List<Product> getProducts() {
        return productPersistence.findAll();
    }
}
