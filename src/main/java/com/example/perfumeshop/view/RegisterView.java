package com.example.perfumeshop.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegisterView implements Initializable
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
    private Button exitButton;
    @FXML
    private Button registerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerButton.setDisable(true);
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(usernameTextField);
        textFields.add(passwordTextField);

        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {

                int numberOfNonEmpty  = 0;
                System.out.println("Action");
                for (TextField textField1 : textFields)
                    if (!textField1.getText().isEmpty())
                    {
                        numberOfNonEmpty++;
                    }
                System.out.println(numberOfNonEmpty);
                progressIndicator.setProgress((double) numberOfNonEmpty/2);
            });

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

        });
    }
}
