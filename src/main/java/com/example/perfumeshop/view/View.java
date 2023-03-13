package com.example.perfumeshop.view;

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
            fxmlLoader.setLocation(View.class.getResource(fxmlFile));
            programRoot = fxmlLoader.load();
            Scene programScene = new Scene(programRoot);
            String css = View.class.getResource("start.css").toExternalForm();
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


//    public static void populateTableProducts(TableView<MenuItem> productTableView, ObservableList<MenuItem> productItems, TableColumn<MenuItem, String> titleColumn,
//                                             TableColumn<MenuItem, Number> ratingColumn, TableColumn<MenuItem, Number> caloriesColumn, TableColumn<MenuItem, Number> proteinColumn,
//                                             TableColumn<MenuItem, Number> fatColumn, TableColumn<MenuItem, Number> sodiumColumn, TableColumn<MenuItem, Number> priceColumn, TableColumn<MenuItem, String> compositionColumn){
//        productItems.clear();
//        productTableView.getItems().clear();
//        titleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));
//        ratingColumn.setCellValueFactory(cellData -> new ReadOnlyFloatWrapper(cellData.getValue().getRating()));
//        caloriesColumn.setCellValueFactory(cellData -> new ReadOnlyFloatWrapper(cellData.getValue().getCalories()));
//        proteinColumn.setCellValueFactory(cellData -> new ReadOnlyFloatWrapper(cellData.getValue().getProtein()));
//        fatColumn.setCellValueFactory(cellData -> new ReadOnlyFloatWrapper(cellData.getValue().getFat()));
//        sodiumColumn.setCellValueFactory(cellData -> new ReadOnlyFloatWrapper(cellData.getValue().getSodium()));
//        priceColumn.setCellValueFactory(cellData -> new ReadOnlyFloatWrapper(cellData.getValue().getPrice()));
//        compositionColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getComponents()));
//        DeliveryService deliveryService = new DeliveryService();
//        productItems.addAll(deliveryService.getProducts());
//        productTableView.setItems(productItems);
//    }
}
