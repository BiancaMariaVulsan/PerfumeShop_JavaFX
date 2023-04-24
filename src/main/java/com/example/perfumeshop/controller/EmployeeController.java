package com.example.perfumeshop.controller;

import com.example.perfumeshop.model.ShopProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    @FXML
    private TableView<ShopProduct> productTableView;
    private final ObservableList<ShopProduct> productItems = FXCollections.observableArrayList();
    @FXML
    private TableColumn<ShopProduct, String> nameColumn;
    @FXML
    private TableColumn<ShopProduct ,String> brandColumn;
    @FXML
    private TableColumn<ShopProduct, Boolean> availabilityColumn;
    @FXML
    private TableColumn<ShopProduct, Number> priceColumn;

    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button filterButton;

    @FXML
    private TextField brandFilter;
    @FXML
    private CheckBox availabilityFilter;
    @FXML
    private TextField priceFilter;

    private final int idShop;
    private final ProductController productPresenter = new ProductController();

    public EmployeeController(int isShop) {
        this.idShop = isShop;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.populateTableProducts(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, idShop);
        addButton.setOnAction(e -> {
            Callback<Class<?>, Object> controllerFactory = type -> {
                if (type == AddProductController.class) {
                    return new AddProductController(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, idShop);
                } else {
                    try {
                        return type.newInstance();
                    } catch (Exception exc) {
                        System.err.println("Could not load register controller " + type.getName());
                        throw new RuntimeException(exc);
                    }
                }
            };
            Controller.loadFXML("/com/example/perfumeshop/add-product-view.fxml", controllerFactory);
        });
        deleteButton.setOnAction(e -> {
            var products = productPresenter.deleteProduct(productTableView.getSelectionModel().getSelectedItem().getProduct(), idShop);
            Controller.populateTableProducts(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, products);
        });
        filterButton.setOnAction(e -> {
            var filteredItems = productPresenter.filterProducts(brandFilter, availabilityFilter, priceFilter, idShop);
            Controller.populateTableProducts(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, filteredItems);
        });
        editButton.setOnAction(e -> {
            ShopProduct product = productTableView.getSelectionModel().getSelectedItem();
            if(product == null) {
                Controller.initAlarmBox("Warning", "Please select the product to be updated!", Alert.AlertType.WARNING);
                return;
            }
            Callback<Class<?>, Object> controllerFactory = type -> {
                if (type == AddProductController.class) {
                    return new AddProductController(product, productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, idShop);
                } else {
                    try {
                        return type.newInstance();
                    } catch (Exception exc) {
                        System.err.println("Could not load register controller " + type.getName());
                        throw new RuntimeException(exc);
                    }
                }
            };
            Controller.loadFXML("/com/example/perfumeshop/add-product-view.fxml", controllerFactory);
        });
    }
}
