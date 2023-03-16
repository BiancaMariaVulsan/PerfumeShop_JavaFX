package com.example.perfumeshop.presenter;

import com.example.perfumeshop.model.Employee;
import com.example.perfumeshop.view.IRegisterView;
import com.example.perfumeshop.view.View;
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
        Employee employee = new Employee(registerView.getFirstNameTextField().getText(),
                registerView.getLastNameTextField().getText(), registerView.getUsernameTextField().getText(),
                registerView.getPasswordTextField().getText(), 1);
        if(EmployeePresenter.addEmployee(employee)) {
            View.initAlarmBox("Successful registration", "You are successfully registered!", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) registerView.getRegisterButton().getScene().getWindow();
            stage.close();
        } else {
            View.initAlarmBox("Error", "An error occurred during the registration, please try again!", Alert.AlertType.ERROR);
        }
    }
}
