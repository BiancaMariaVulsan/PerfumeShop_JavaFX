package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Product;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.List;

public interface IProductPresenter {
    List<Product> getProducts();
    List<Product> getProducts(int idShop);
    List<Product> filterProducts(TextField nameFilter, TextField brandFilter, CheckBox availabilityFilter, TextField priceFilter);
    List<Product> filterProducts(TextField brandFilter, CheckBox availabilityFilter, TextField priceFilter, int shopId);
    public boolean addProduct(TextField nameText, TextField brandText, CheckBox availabilityCheck, TextField priceText, int shopId);
    boolean deleteProduct(Product product, int shopId);
    boolean updateProduct(int productToUpdateId, TextField nameText, TextField brandText, CheckBox availabilityCheck, TextField priceText, int shopId);
    List<Product> sortByName();
    List<Product> sortByPrice();
}
