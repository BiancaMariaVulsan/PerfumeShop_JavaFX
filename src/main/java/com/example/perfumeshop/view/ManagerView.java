package com.example.perfumeshop.view;

import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.presenter.IProductPresenter;
import com.example.perfumeshop.presenter.Presenter;
import com.example.perfumeshop.presenter.ProductPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManagerView implements Initializable {
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
    private TextField nameFilter;
    @FXML
    private TextField brandFilter;
    @FXML
    private CheckBox availabilityFilter;
    @FXML
    private TextField priceFilter;

    @FXML
    private Button filterButton;
    @FXML
    private Button sortNameButton;
    @FXML
    private Button sortPriceButton;

    IProductPresenter productPresenter = new ProductPresenter();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Presenter.populateTableProducts(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn);

        filterButton.setOnAction(e -> {
            String name = nameFilter.getText();
            String brand = brandFilter.getText();
            boolean availability = availabilityFilter.isSelected();
            float price;
            if(name.isEmpty()) {
                name = "";
            }
            try {
                price = Float.parseFloat(priceFilter.getText());
            } catch (NumberFormatException exception) {
                price = -1;
            }
            List<Product> filteredItems = productPresenter.filterProducts(name, brand, availability, price);
            Presenter.populateTableProducts(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, filteredItems);
        });
        sortNameButton.setOnAction(e -> {
            List<Product> sortedItems = productPresenter.sortByName();
            Presenter.populateTableProducts(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, sortedItems);
        });
        sortPriceButton.setOnAction(e -> {
            List<Product> sortedItems = productPresenter.sortByPrice();
            Presenter.populateTableProducts(productTableView, productItems, nameColumn, brandColumn, availabilityColumn, priceColumn, sortedItems);
        });
    }
}
