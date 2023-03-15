package com.example.perfumeshop.view;

import com.example.perfumeshop.presenter.LoginPresenter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class LogInView {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button signInButton;
    @FXML
    private Button registerButton;

    boolean isAdmin = true;
    boolean isEmployee = true;

    private final LoginPresenter loginPresenter = new LoginPresenter();

    @FXML
    public void initialize() {
        registerButton.setOnAction(e -> {
            Callback<Class<?>, Object> controllerFactory = loginPresenter::register;
            View.loadFXML("/com/example/perfumeshop/register-view.fxml", controllerFactory);
        });
        signInButton.setOnAction(actionEvent -> {
            if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty())
            {
                View.initAlarmBox("Error", "You must complete both username and password fields!", Alert.AlertType.ERROR);
                return;

            }
            if (usernameTextField.getText().equals("admin") && passwordTextField.getText().equals("admin")) {
                isAdmin = true;
                isEmployee = false;
            } else  if (usernameTextField.getText().equals("employee") && passwordTextField.getText().equals("employee")) {
                isEmployee = true;
                isAdmin = false;
            } else {
//                if (LoginPresenter.checkClientExists(usernameTextField.getText(),passwordTextField.getText())){
//                    isAdmin = false;
//                    isEmployee = false;
//                }
//                else {
//                    View.initAlarmBox("Error", "There is no user with this username and password!", Alert.AlertType.ERROR);
//                    return;
//                }
            }
            if (isAdmin){
                Callback<Class<?>, Object> controllerFactory = type -> {
                    if (type ==AdminView.class) {
                        return new AdminView();
                    } else {
                        try {
                            return type.newInstance();
                        } catch (Exception exc) {
                            System.err.println("Could not load admin controller "+type.getName());
                            throw new RuntimeException(exc);
                        }
                    }
                };
                View.loadFXML("admin-view.fxml", controllerFactory);
            } else if (isEmployee) {
                Callback<Class<?>, Object> controllerFactory = type -> {
                    if (type ==EmployeeView.class) {
                        return new EmployeeView();
                    } else {
                        try {
                            return type.newInstance();
                        } catch (Exception exc) {
                            System.err.println("Could not load employee controller " + type.getName());
                            throw new RuntimeException(exc);
                        }
                    }
                };
                View.loadFXML("employee-view.fxml", controllerFactory);
            } else {
//                Client client = LoginService.getClientByUsername(usernameTextField.getText());
//
//                Callback<Class<?>, Object> controllerFactory = type -> {
//                    if (type == ClientController.class) {
//                        return new ClientController(deliveryService, client);
//                    } else {
//                        try {
//                            return type.newInstance();
//                        } catch (Exception exc) {
//                            System.err.println("Could not load user controller "+type.getName());
//                            throw new RuntimeException(exc);
//                        }
//                    }
//                };
//                View.loadFXML("manager-view.fxml", controllerFactory);
            }
        });
    }
}