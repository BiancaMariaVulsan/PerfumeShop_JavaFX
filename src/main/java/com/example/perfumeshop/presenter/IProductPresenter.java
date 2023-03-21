package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.model.ShopProduct;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.List;

public interface IProductPresenter {
    List<Product> getProducts();
    List<ShopProduct> getProducts(int idShop);
    List<Product> filterProducts(TextField nameFilter, TextField brandFilter, CheckBox availabilityFilter, TextField priceFilter);
    List<ShopProduct> filterProducts(TextField brandFilter, CheckBox availabilityFilter, TextField priceFilter, int shopId);
    public boolean addProduct(TextField nameText, TextField brandText, TextField stockText, TextField priceText, int shopId);
    boolean deleteProduct(Product product, int shopId);
    boolean updateProduct(int productToUpdateId, TextField nameText, TextField brandText, CheckBox availabilityCheck, TextField priceText, int shopId);
    boolean updateProductInShop(int productToUpdateId, TextField stock, int shopId);
    List<Product> sortByName();
    List<Product> sortByPrice();
}
