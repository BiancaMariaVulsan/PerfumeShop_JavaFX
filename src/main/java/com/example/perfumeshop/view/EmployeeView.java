package com.example.perfumeshop.view;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.presenter.PersonPresenter;
import com.example.perfumeshop.presenter.Presenter;
import com.example.perfumeshop.presenter.ProductPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeView implements Initializable {
    @FXML
    private TableView<Product> productTableView;
    private ObservableList<Product> productItems = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product ,String> brandColumn;
    @FXML
    private TableColumn<Product, Boolean> availabilityColumn;
    @FXML
    private TableColumn<Product, Number> priceColumn;

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
    private final ProductPresenter productPresenter = new ProductPresenter();

    public EmployeeView(int isShop) {
        this.idShop = isShop;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Presenter.populateTableProducts(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, idShop);
        addButton.setOnAction(e -> {
            Callback<Class<?>, Object> controllerFactory = type -> {
                if (type == AddProductView.class) {
                    return new AddProductView(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, idShop);
                } else {
                    try {
                        return type.newInstance();
                    } catch (Exception exc) {
                        System.err.println("Could not load register controller " + type.getName());
                        throw new RuntimeException(exc);
                    }
                }
            };
            Presenter.loadFXML("/com/example/perfumeshop/add-product-view.fxml", controllerFactory);
        });
        deleteButton.setOnAction(e -> {
            Product product = productTableView.getSelectionModel().getSelectedItem();
            if(product == null) {
                Presenter.initAlarmBox("Warning", "Please select the product to be deleted!", Alert.AlertType.WARNING);
                return;
            }
            if(productPresenter.deleteProduct(product, idShop)) {
                Presenter.populateTableProducts(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, idShop);
            } else {
                Presenter.initAlarmBox("Warning", "Delete operation failed, please try again!", Alert.AlertType.WARNING);
            }
        });
        filterButton.setOnAction(e -> {
            String brand = brandFilter.getText();
            boolean availability = availabilityFilter.isSelected();
            float price;
            if(brand.isEmpty()) {
                brand = "";
            }
            try {
                price = Float.parseFloat(priceFilter.getText());
            } catch (NumberFormatException exception) {
                price = -1;
            }
            List<Product> filteredItems = productPresenter.filterProducts(brand, availability, price);
            Presenter.populateTableProducts(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, filteredItems);
        });
        editButton.setOnAction(e -> {
            Product product = productTableView.getSelectionModel().getSelectedItem();
            if(product == null) {
                Presenter.initAlarmBox("Warning", "Please select the product to be updated!", Alert.AlertType.WARNING);
                return;
            }
            Callback<Class<?>, Object> controllerFactory = type -> {
                if (type == AddProductView.class) {
                    return new AddProductView(product, productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, idShop);
                } else {
                    try {
                        return type.newInstance();
                    } catch (Exception exc) {
                        System.err.println("Could not load register controller " + type.getName());
                        throw new RuntimeException(exc);
                    }
                }
            };
            Presenter.loadFXML("/com/example/perfumeshop/add-product-view.fxml", controllerFactory);
        });
    }
}
