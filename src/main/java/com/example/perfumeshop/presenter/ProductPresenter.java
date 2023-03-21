package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.model.persistence.ProductPersistence;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductPresenter implements IProductPresenter {
    ProductPersistence productPersistence = new ProductPersistence();
    Map<Integer, List<Product>> productsMap = new HashMap<>();

    @Override
    public List<Product> getProducts() {
        return productPersistence.findAll();
    }

    @Override
    public List<Product> getProducts(int idShop) {
        productsMap = getProductsMap();
        return productsMap.get(idShop);
    }
    @Override
    public List<Product> filterProducts(TextField nameFilter, TextField brandFilter, CheckBox availabilityFilter, TextField priceFilter) {
        String name = nameFilter.getText();
        String brand = brandFilter.getText();
        boolean availability = availabilityFilter.isSelected();
        double price;
        if(name.isEmpty()) {
            name = "";
        }
        if(brand.isEmpty()) {
            brand = "";
        }
        try {
            price = Double.parseDouble(priceFilter.getText());
        } catch (NumberFormatException exception) {
            price = -1;
        }

        List<Product> products = getProducts();
        String finalName = name;
        String finalBrand = brand;
        double finalPrice = price;
        return products.stream()
                .filter(it -> finalName.equals("") || it.getName().contains(finalName))
                .filter(it -> finalBrand.equals("") || it.getBrand().contains(finalBrand))
                .filter(it -> !availability || it.getAvailability())
                .filter(it -> finalPrice == -1 || it.getPrice() <= finalPrice)
                .collect(Collectors.toList());
    }
    @Override
    public List<Product> filterProducts(TextField brandFilter, CheckBox availabilityFilter, TextField priceFilter, int shopId) {
        String brand = brandFilter.getText();
        boolean availability = availabilityFilter.isSelected();
        double price;
        if(brand.isEmpty()) {
            brand = "";
        }
        try {
            price = Double.parseDouble(priceFilter.getText());
        } catch (NumberFormatException exception) {
            price = -1;
        }

        List<Product> products = getProductsMap().get(shopId);
        String finalBrand = brand;
        double finalPrice = price;
        return products.stream()
                .filter(it -> finalBrand.equals("") || it.getBrand().contains(finalBrand))
                .filter(it -> !availability || it.getAvailability())
                .filter(it -> finalPrice == -1 || it.getPrice() <= finalPrice)
                .collect(Collectors.toList());
    }

    @Override
    public boolean addProduct(TextField nameText, TextField brandText, CheckBox availabilityCheck, TextField priceText, int shopId) {
        try {
            Product product = new Product(nameText.getText(), brandText.getText(), availabilityCheck.isSelected(), Double.parseDouble(priceText.getText()));
            productPersistence.insert(product);
            Product insertedProduct = productPersistence.findAll()
                    .stream().filter(p -> p.getName().equals(product.getName()) && p.getBrand().equals(product.getBrand())
                    && p.getPrice() == product.getPrice() && p.getAvailability() == product.getAvailability())
                    .findFirst()
                    .orElse(null);
            productPersistence.insertProductInShop(shopId, insertedProduct.getId());
            return true;
        } catch (Exception e) {
            Presenter.initAlarmBox("Error", "Something went wrong when trying to add the product. Please make sure you insert valid properties!", Alert.AlertType.ERROR);
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Product product, int shopId) {
        if(product == null) {
            Presenter.initAlarmBox("Warning", "Please select the product to be deleted!", Alert.AlertType.WARNING);
            return false;
        }
        try {
            productPersistence.delete(product);
            productPersistence.deleteProductFromShop(shopId, product.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateProduct(int productToUpdateId, TextField nameText, TextField brandText, CheckBox availabilityCheck, TextField priceText, int shopId) {
        try {
            Product product = new Product(productToUpdateId, nameText.getText(), brandText.getText(), availabilityCheck.isSelected(), Double.parseDouble(priceText.getText()));
            productPersistence.update(product);
            return true;
        } catch (Exception e) {
            Presenter.initAlarmBox("Error", "Something went wrong when trying to update the product. Please make sure you insert valid properties!", Alert.AlertType.ERROR);
            return false;
        }
    }

    @Override
    public List<Product> sortByName() {
        return this.getProducts().stream().sorted(Comparator.comparing(Product::getName)).toList();
    }

    @Override
    public List<Product> sortByPrice() {
        return this.getProducts().stream().sorted(Comparator.comparing(Product::getPrice)).toList();
    }

    private Map<Integer, List<Product>> getProductsMap() {
        return productPersistence.getShopProducts();
    }
}
