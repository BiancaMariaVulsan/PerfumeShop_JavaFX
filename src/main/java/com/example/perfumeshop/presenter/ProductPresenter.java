package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.model.persistence.ProductPersistence;

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

    public List<Product> filterProducts(String name, String brand, boolean availability, float price) {
        List<Product> products = getProducts();
        return products.stream()
                .filter(it -> name.equals("") || it.getName().contains(name))
                .filter(it -> brand.equals("") || it.getBrand().contains(brand))
                .filter(it -> !availability || it.getAvailability())
                .filter(it -> price == -1 || it.getPrice() <= price)
                .collect(Collectors.toList());
    }

    public List<Product> filterProducts(String brand, boolean availability, float price, int shopId) {
        List<Product> products = getProductsMap().get(shopId);
        return products.stream()
                .filter(it -> brand.equals("") || it.getBrand().contains(brand))
                .filter(it -> !availability || it.getAvailability())
                .filter(it -> price == -1 || it.getPrice() <= price)
                .collect(Collectors.toList());
    }

    @Override
    public boolean addProduct(Product product, int shopId) {
        try {
            productPersistence.insert(product);
            Product insertedProduct = productPersistence.findAll()
                    .stream().filter(p -> p.getName().equals(product.getName()) && p.getBrand().equals(product.getBrand())
                    && p.getPrice() == product.getPrice() && p.getAvailability() == product.getAvailability())
                    .findFirst()
                    .orElse(null);
            productPersistence.insertProductInShop(shopId, insertedProduct.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Product product, int shopId) {
        try {
            productPersistence.delete(product);
            productPersistence.deleteProductFromShop(shopId, product.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product product, int shopId) {
        try {
            productPersistence.update(product);
            return true;
        } catch (Exception e) {
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
