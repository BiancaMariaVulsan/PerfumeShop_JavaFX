package com.example.perfumeshop.controller;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.persistence.PersonPersistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private TableView<Person> personTableView;
    private final ObservableList<Person> personItems = FXCollections.observableArrayList();
    private static final PersonPersistence personPersistence = new PersonPersistence();
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, String> roleColumn;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.populateTablePersons(personTableView, personItems, firstNameColumn, lastNameColumn, roleColumn);
        addButton.setOnAction(e -> {
            Callback<Class<?>, Object> controllerFactory = type -> {
                if (type == RegisterController.class) {
                    return new RegisterController(personTableView, personItems, firstNameColumn, lastNameColumn, roleColumn);
                } else {
                    try {
                        return type.newInstance();
                    } catch (Exception exc) {
                        System.err.println("Could not load register controller " + type.getName());
                        throw new RuntimeException(exc);
                    }
                }
            };
            Controller.loadFXML("/com/example/perfumeshop/register-view.fxml", controllerFactory);
        });
        deleteButton.setOnAction(e -> {
            var person = personTableView.getSelectionModel().getSelectedItem();
            if(person == null) {
                Controller.initAlarmBox("Warning", "Please select the product to be deleted!", Alert.AlertType.WARNING);
                return;
            }
            if(deletePersons(person)) {
                Controller.populateTablePersons(personTableView, personItems, firstNameColumn, lastNameColumn, roleColumn);
            } else {
                Controller.initAlarmBox("Warning", "Delete operation failed, please try again!", Alert.AlertType.WARNING);
            }
        });
        editButton.setOnAction(e -> {
            Person item = personTableView.getSelectionModel().getSelectedItem();
            if(item == null) {
                Controller.initAlarmBox("Warning", "Please select the product to be edited!", Alert.AlertType.WARNING);
                return;
            }
            Callback<Class<?>, Object> controllerFactory = type -> {
                if (type == RegisterController.class) {
                    return new RegisterController(item, personTableView, personItems, firstNameColumn, lastNameColumn, roleColumn);
                } else {
                    try {
                        return type.newInstance();
                    } catch (Exception exc) {
                        System.err.println("Could not load register controller " + type.getName());
                        throw new RuntimeException(exc);
                    }
                }
            };
            Controller.loadFXML("/com/example/perfumeshop/register-view.fxml", controllerFactory);
        });
    }
    private static boolean deletePersons(Person person) {
        try {
            personPersistence.delete(person);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
