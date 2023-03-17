package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.Product;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class Presenter {
    public static void loadFXML(String fxmlFile, Callback<Class<?>, Object> controllerFactory) {
        Stage programStage = new Stage();
        Parent programRoot;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Presenter.class.getResource(fxmlFile));
            fxmlLoader.setControllerFactory(controllerFactory);
            var path = Presenter.class.getResource(fxmlFile);
            fxmlLoader.setLocation(path);
            programRoot = fxmlLoader.load();
            Scene programScene = new Scene(programRoot);
            String css = Presenter.class.getResource("/com/example/perfumeshop/start.css").toExternalForm();
            programScene.getStylesheets().add(css);
            programStage.setTitle("Running Program");
            programStage.setScene(programScene);
            programStage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void initAlarmBox(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        confirm.setDefaultButton(false);
        confirm.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        alert.showAndWait();
    }


    public static void populateTableProducts(TableView<Product> productTableView, ObservableList<Product> productItems, TableColumn<Product, String> nameColumn,
                                             TableColumn<Product ,String> brandColumn, TableColumn<Product, Boolean> availabilityColumn, TableColumn<Product, Number> priceColumn){
        productItems.clear();
        productTableView.getItems().clear();
        nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        brandColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getBrand()));
        availabilityColumn.setCellValueFactory(cellData -> new ReadOnlyBooleanWrapper(cellData.getValue().getAvailability()));
        priceColumn.setCellValueFactory(cellData -> new ReadOnlyDoubleWrapper(cellData.getValue().getPrice()));
        IProductPresenter productPresenter = new ProductPresenter();
        productItems.addAll(productPresenter.getProducts());
        productTableView.setItems(productItems);
    }

    public static void populateTablePersons(TableView<Person> personTableView, ObservableList<Person> personItems, TableColumn<Person, String> firstNameColumn,
                                            TableColumn<Person ,String> lastNameColumn, TableColumn<Person, String> roleColumn){
        personItems.clear();
        personTableView.getItems().clear();
        firstNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLastName()));
        roleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getRole().toString()));
        IPersonPresenter personPresenter = new PersonPresenter();
        personItems.addAll(personPresenter.getPersons());
        personTableView.setItems(personItems);
    }
}
