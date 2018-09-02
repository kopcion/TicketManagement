package controller;

import Database.DbEngine;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class Registration {

    @FXML
    public PasswordField repasswordText;
    public PasswordField passwordText;
    public TextField loginText;
    public TextField nameText;
    public TextField surnameText;
    public Label passwordError;


    public void handleConfirmButton(){
        if(nameText.getText().length() == 0 || surnameText.getText().length() == 0){
            passwordError.setText("Name and surname fields are obligatory");
            passwordError.setVisible(true);
        } else if(loginText.getText().length() < 6) {
            passwordError.setText("Login has to have at least 6 characters");
            passwordError.setVisible(true);
        } else if(passwordText.getText().length() < 6) {
            passwordError.setText("Password has to be at least 6 charaters long");
            passwordError.setVisible(true);
        } else if(!passwordText.getText().equals(repasswordText.getText())){
            passwordError.setText("Password does not match");
            passwordError.setVisible(true);
        } else {
            passwordError.setVisible(false);
            DbEngine.register(loginText.getText(), passwordText.getText(), nameText.getText(), surnameText.getText());
            passwordError.getScene().getWindow().hide();
        }
        System.out.println(repasswordText.getText());
        System.out.println(passwordText.getText());

    }

}
