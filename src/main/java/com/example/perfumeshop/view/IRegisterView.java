package com.example.perfumeshop.view;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public interface IRegisterView {

    public ProgressIndicator getProgressIndicator();

    public void setProgressIndicator(ProgressIndicator progressIndicator);

    public CheckBox getTermsCheckBox();

    public void setTermsCheckBox(CheckBox termsCheckBox);

    public TextField getUsernameTextField();

    public void setUsernameTextField(TextField usernameTextField);

    public TextField getPasswordTextField();

    public void setPasswordTextField(TextField passwordTextField);

    public Button getExitButton();

    public void setExitButton(Button exitButton);

    public Button getRegisterButton();
    public void setRegisterButton(Button registerButton);
    public TextField getFirstNameTextField();

    public void setFirstNameTextField(TextField firstNameTextField);

    public TextField getLastNameTextField();

    public void setLastNameTextField(TextField lastNameTextField);
}
