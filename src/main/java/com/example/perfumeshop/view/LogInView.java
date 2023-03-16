package com.example.perfumeshop.view;

import com.example.perfumeshop.presenter.LoginPresenter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class LogInView implements ILoginView {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button signInButton;
    @FXML
    private Button registerButton;

    private final LoginPresenter loginPresenter;

    public LogInView() {
        this.loginPresenter = new LoginPresenter(this);
    }

    @FXML
    public void initialize() {
        registerButton.setOnAction(e -> {
            Callback<Class<?>, Object> controllerFactory = loginPresenter::register;
            View.loadFXML("/com/example/perfumeshop/register-view.fxml", controllerFactory);
        });
        signInButton.setOnAction(actionEvent -> {
            loginPresenter.signIn();
        });
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
}