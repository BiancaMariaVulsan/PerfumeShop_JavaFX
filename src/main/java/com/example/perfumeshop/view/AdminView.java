package com.example.perfumeshop.view;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.presenter.Presenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminView implements Initializable {
    @FXML
    private TableView<Person> personTableView;
    private ObservableList<Person> personItems = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, String> roleColumn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Presenter.populateTablePersons(personTableView, personItems, firstNameColumn, lastNameColumn, roleColumn);
    }
}
