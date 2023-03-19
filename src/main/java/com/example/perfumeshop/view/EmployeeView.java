package com.example.perfumeshop.view;

import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.presenter.Presenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;
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
    private Button filterButton;

    private final int idShop;

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

        });
        filterButton.setOnAction(e -> {

        });
    }
}
