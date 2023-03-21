package com.example.perfumeshop.view;

import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.presenter.IProductPresenter;
import com.example.perfumeshop.presenter.Presenter;
import com.example.perfumeshop.presenter.ProductPresenter;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    private final TableView<Product> productTableView;
    private final ObservableList<Product> productItems;
    @FXML
    private final TableColumn<Product, String> nameColumn;
    @FXML
    private final TableColumn<Product ,String> brandColumn;
    @FXML
    private final TableColumn<Product, Boolean> availabilityColumn;
    @FXML
    private final TableColumn<Product, Number> priceColumn;
    private final int idShop;
    private final boolean isEditing;
    private Product productToUpdate;

    IProductPresenter productPresenter = new ProductPresenter(); //todo: change similar to register if needed

    public AddProductView(TableView<Product> productTableView, ObservableList<Product> productItems,
                          TableColumn<Product, String> nameColumn, TableColumn<Product, String> brandColumn,
                          TableColumn<Product, Boolean> availabilityColumn, TableColumn<Product, Number> priceColumn,
                          int idShop) {
        isEditing = false;
        this.productTableView = productTableView;
        this.productItems = productItems;
        this.nameColumn = nameColumn;
        this.brandColumn = brandColumn;
        this.availabilityColumn = availabilityColumn;
        this.priceColumn = priceColumn;
        this.idShop = idShop;
    }

    public AddProductView(Product product, TableView<Product> productTableView, ObservableList<Product> productItems,
                          TableColumn<Product, String> nameColumn, TableColumn<Product, String> brandColumn,
                          TableColumn<Product, Boolean> availabilityColumn, TableColumn<Product, Number> priceColumn,
                          int idShop) {
        isEditing = true;
        this.productTableView = productTableView;
        this.productItems = productItems;
        this.nameColumn = nameColumn;
        this.brandColumn = brandColumn;
        this.availabilityColumn = availabilityColumn;
        this.priceColumn = priceColumn;
        this.idShop = idShop;
        this.productToUpdate = product;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(isEditing) {
            nameText.setText(productToUpdate.getName());
            brandText.setText(productToUpdate.getBrand());
            if(productToUpdate.getAvailability()) {
                availabilityCheck.isSelected();
            }
            priceText.setText(String.valueOf(productToUpdate.getPrice()));
        }

        saveButton.setOnAction(e -> {
            if(isEditing) {
                productPresenter.updateProduct(productToUpdate.getId(),nameText, brandText, availabilityCheck, priceText, idShop);
                Presenter.populateTableProducts(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, idShop);
            } else {
                productPresenter.addProduct(nameText, brandText, availabilityCheck, priceText, idShop);
                Presenter.populateTableProducts(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, idShop);
            }
        });
    }
}
