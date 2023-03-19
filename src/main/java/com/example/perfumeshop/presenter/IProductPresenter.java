package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.Product;

import java.util.List;

public interface IProductPresenter {
    List<Product> getProducts();
    List<Product> getProducts(int idShop);
    List<Product> filterProducts(String name, String brand, boolean availability, float price);
    public List<Product> filterProducts(String brand, boolean availability, float price);
    boolean addProduct(Product product, int shopId);
    boolean deleteProduct(Product product, int shopId);
    boolean updateProduct(Product product, int shopId);
}
