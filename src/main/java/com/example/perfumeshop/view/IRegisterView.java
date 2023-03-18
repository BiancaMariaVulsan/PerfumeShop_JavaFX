package com.example.perfumeshop.view;

import com.example.perfumeshop.model.Role;
import javafx.scene.control.*;

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

    public ChoiceBox<Role> getRoleChoiceBox();

    public void setRoleChoiceBox(ChoiceBox<Role> roleChoiceBox);
}
