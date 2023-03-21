package com.example.perfumeshop.view;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.Role;
import com.example.perfumeshop.model.Shop;
import javafx.scene.control.*;

public interface IRegisterView {

    ProgressIndicator getProgressIndicator();

    void setProgressIndicator(ProgressIndicator progressIndicator);

    CheckBox getTermsCheckBox();

    void setTermsCheckBox(CheckBox termsCheckBox);

    TextField getUsernameTextField();

    void setUsernameTextField(TextField usernameTextField);

    TextField getPasswordTextField();

    void setPasswordTextField(TextField passwordTextField);

    Button getExitButton();

    void setExitButton(Button exitButton);

    Button getRegisterButton();
    void setRegisterButton(Button registerButton);
    TextField getFirstNameTextField();

    void setFirstNameTextField(TextField firstNameTextField);

    TextField getLastNameTextField();

    void setLastNameTextField(TextField lastNameTextField);

    ChoiceBox<Role> getRoleChoiceBox();

    TableColumn<Person, String> getRoleColumn();
    ChoiceBox<Shop> getShopChoiceBox();
}
