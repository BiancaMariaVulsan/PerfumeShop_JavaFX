package com.example.perfumeshop.view;

import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.presenter.IProductPresenter;
import com.example.perfumeshop.presenter.ProductPresenter;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class View {
    public static void loadFXML(String fxmlFile, Callback<Class<?>, Object> controllerFactory) {
        Stage programStage = new Stage();
        Parent programRoot;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(View.class.getResource(fxmlFile));
            fxmlLoader.setControllerFactory(controllerFactory);
            var path = View.class.getResource(fxmlFile);
            fxmlLoader.setLocation(path);
            programRoot = fxmlLoader.load();
            Scene programScene = new Scene(programRoot);
            String css = View.class.getResource("/com/example/perfumeshop/start.css").toExternalForm();
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
}
