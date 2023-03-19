package com.example.perfumeshop.view;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.presenter.IProductPresenter;
import com.example.perfumeshop.presenter.Presenter;
import com.example.perfumeshop.presenter.ProductPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Builder;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductView implements Initializable {
    @FXML
    private TextField nameText;
    @FXML
    private TextField brandText;
    @FXML
    private CheckBox availabilityCheck;
    @FXML
    private TextField priceText;
    @FXML
    private Button saveButton;

    @FXML
    private TableView<Product> productTableView;
    private ObservableList<Product> productItems;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product ,String> brandColumn;
    @FXML
    private TableColumn<Product, Boolean> availabilityColumn;
    @FXML
    private TableColumn<Product, Number> priceColumn;
    private int idShop;

    IProductPresenter productPresenter = new ProductPresenter(); //todo: change similar to register if needed

    public AddProductView(TableView<Product> productTableView, ObservableList<Product> productItems,
                          TableColumn<Product, String> nameColumn, TableColumn<Product, String> brandColumn,
                          TableColumn<Product, Boolean> availabilityColumn, TableColumn<Product, Number> priceColumn,
                          int idShop) {
        this.productTableView = productTableView;
        this.productItems = productItems;
        this.nameColumn = nameColumn;
        this.brandColumn = brandColumn;
        this.availabilityColumn = availabilityColumn;
        this.priceColumn = priceColumn;
        this.idShop = idShop;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveButton.setOnAction(e -> {
            //todo: make sure the price is valid
            Product product = new Product(nameText.getText(), brandText.getText(), availabilityCheck.isSelected(), Double.parseDouble(priceText.getText()));
            productPresenter.addProduct(product);
            Presenter.populateTableProducts(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, idShop);
        });
    }
}
