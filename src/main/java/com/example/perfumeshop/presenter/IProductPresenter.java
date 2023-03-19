package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.Product;

import java.util.List;

public interface IProductPresenter {
    public List<Product> getProducts();
    public List<Product> getProducts(int idShop);
    public List<Product> filterProducts(String name, String brand, boolean availability, float price);
}
