package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Employee;
import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.Role;
import com.example.perfumeshop.model.persistence.PersonPersistence;
import com.example.perfumeshop.view.IRegisterView;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RegisterPresenter implements IRegisterPresenter {
    private final IRegisterView registerView;

    public RegisterPresenter(IRegisterView registerView) {
        this.registerView = registerView;
    }

    public void setProgressIndicator() {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(registerView.getUsernameTextField());
        textFields.add(registerView.getPasswordTextField());
        textFields.add(registerView.getFirstNameTextField());
        textFields.add(registerView.getLastNameTextField());

        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {

                int numberOfNonEmpty = 0;
                for (TextField textField1 : textFields)
                    if (!textField1.getText().isEmpty()) {
                        numberOfNonEmpty++;
                    }
                registerView.getProgressIndicator().setProgress((double) numberOfNonEmpty / textFields.size());
            });
        }
    }

    public void register() {
        Role role = registerView.getRoleChoiceBox().getValue();
        Person person = new Employee(registerView.getFirstNameTextField().getText(),
                registerView.getLastNameTextField().getText(), registerView.getUsernameTextField().getText(),
                registerView.getPasswordTextField().getText(), 1);
        if(role.equals(Role.MANAGER)) {
            person = new Person(registerView.getFirstNameTextField().getText(),
                    registerView.getLastNameTextField().getText(), Role.MANAGER, registerView.getUsernameTextField().getText(),
                    registerView.getPasswordTextField().getText());
        } else if(role.equals(Role.ADMIN)) {
            person = new Person(registerView.getFirstNameTextField().getText(),
                    registerView.getLastNameTextField().getText(), Role.ADMIN, registerView.getUsernameTextField().getText(),
                    registerView.getPasswordTextField().getText());
        }
        if(PersonPresenter.addPerson(person)) {
            Presenter.initAlarmBox("Successful registration", "You are successfully registered!", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) registerView.getRegisterButton().getScene().getWindow();
            stage.close();
        } else {
            Presenter.initAlarmBox("Error", "An error occurred during the registration, please try again!", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void updatePerson(Person personToUpdate) {
        personToUpdate = new Person(personToUpdate.getId(), registerView.getFirstNameTextField().getText(),
                registerView.getLastNameTextField().getText(), registerView.getRoleChoiceBox().getValue(),
                registerView.getUsernameTextField().getText(), registerView.getPasswordTextField().getText());
        PersonPersistence personPresenter = new PersonPersistence();
        if(personPresenter.update(personToUpdate)) {
            Presenter.initAlarmBox("Successful registration", "Person successfully updated!", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) registerView.getRegisterButton().getScene().getWindow();
            stage.close();
        } else {
            Presenter.initAlarmBox("Error", "An error occurred during the registration, please try again!", Alert.AlertType.ERROR);
        }
    }
}
