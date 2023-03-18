package com.example.perfumeshop.view;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.Role;
import com.example.perfumeshop.presenter.IRegisterPresenter;
import com.example.perfumeshop.presenter.Presenter;
import com.example.perfumeshop.presenter.RegisterPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegisterView implements Initializable, IRegisterView
{
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private CheckBox termsCheckBox;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private Button exitButton;
    @FXML
    private Button registerButton;
    @FXML
    private ChoiceBox<Role> roleChoiceBox;
    
    private final IRegisterPresenter registerPresenter;
    private Person personToUpdate;
    private boolean isEditing = false;
    @FXML
    private TableView<Person> personTableView;
    private ObservableList<Person> personItems = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, String> roleColumn;

    public RegisterView() {
        this.isEditing = false;
        this.registerPresenter = new RegisterPresenter(this);
    }

    public RegisterView(Person item, TableView<Person> personTableView, ObservableList<Person> personItems,
                        TableColumn<Person, String> firstNameColumn, TableColumn<Person, String> lastNameColumn,
                        TableColumn<Person, String> roleColumn) {
        this.isEditing = true;
        this.registerPresenter = new RegisterPresenter(this);
        this.personToUpdate = item;
        this.personTableView = personTableView;
        this.personItems = personItems;
        this.firstNameColumn = firstNameColumn;
        this.lastNameColumn = lastNameColumn;
        this.roleColumn = roleColumn;
    }

    public RegisterView(TableView<Person> personTableView, ObservableList<Person> personItems,
                        TableColumn<Person, String> firstNameColumn, TableColumn<Person, String> lastNameColumn,
                        TableColumn<Person, String> roleColumn) {
        this.isEditing = false;
        this.registerPresenter = new RegisterPresenter(this);
        this.personTableView = personTableView;
        this.personItems = personItems;
        this.firstNameColumn = firstNameColumn;
        this.lastNameColumn = lastNameColumn;
        this.roleColumn = roleColumn;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerButton.setDisable(true);
        registerPresenter.setProgressIndicator();
        initCheckBox();

        if(this.isEditing)
        {
            firstNameTextField.setText(personToUpdate.getFirstName());
            lastNameTextField.setText(personToUpdate.getLastName());
            usernameTextField.setText(personToUpdate.getUsername());
            passwordTextField.setText(personToUpdate.getPassword());
            roleChoiceBox.setValue(personToUpdate.getRole());
        }

        exitButton.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setContentText("Are you sure you want to exit? All progress will be lost.");
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(false);
            confirm.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                Stage stage = (Stage) registerButton.getScene().getWindow();
                stage.close();
            }
        });
        termsCheckBox.setOnAction(actionEvent -> {
            if (termsCheckBox.isSelected())
            {
                registerButton.setDisable(false);
            }
            else {
                registerButton.setDisable(true);
            }
        });
        registerButton.setOnAction(actionEvent -> {
            if(!isEditing) {
                registerPresenter.register();
                Presenter.populateTablePersons(personTableView, personItems, firstNameColumn, lastNameColumn, roleColumn);
            } else {
                registerPresenter.updatePerson(personToUpdate);
                Presenter.populateTablePersons(personTableView, personItems, firstNameColumn, lastNameColumn, roleColumn);
            }
        });
    }
    private void initCheckBox() {
        roleChoiceBox.getItems().add(Role.EMPLOYEE);
        roleChoiceBox.getItems().add(Role.MANAGER);
        roleChoiceBox.getItems().add(Role.ADMIN);
        roleChoiceBox.setValue(Role.EMPLOYEE);
    }
    public ProgressIndicator getProgressIndicator() {
        return progressIndicator;
    }

    public void setProgressIndicator(ProgressIndicator progressIndicator) {
        this.progressIndicator = progressIndicator;
    }

    public CheckBox getTermsCheckBox() {
        return termsCheckBox;
    }

    public void setTermsCheckBox(CheckBox termsCheckBox) {
        this.termsCheckBox = termsCheckBox;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public void setUsernameTextField(TextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(TextField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public void setExitButton(Button exitButton) {
        this.exitButton = exitButton;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(Button registerButton) {
        this.registerButton = registerButton;
    }
    public TextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public void setFirstNameTextField(TextField firstNameTextField) {
        this.firstNameTextField = firstNameTextField;
    }

    public TextField getLastNameTextField() {
        return lastNameTextField;
    }

    public void setLastNameTextField(TextField lastNameTextField) {
        this.lastNameTextField = lastNameTextField;
    }

    public ChoiceBox<Role> getRoleChoiceBox() {
        return roleChoiceBox;
    }

    public void setRoleChoiceBox(ChoiceBox<Role> roleChoiceBox) {
        this.roleChoiceBox = roleChoiceBox;
    }
}
