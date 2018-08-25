package controller;

import Database.DbEngine;
import exceptions.LoginException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    @FXML
    public Button loginButton;
    public TextField loginField;
    public TextField passwordField;
    public Label loginLabel;

    boolean initiated = false;
    Stage window = new Stage();
    Parent root;
    SceneController sceneController;

    public void initiate(){
        if(initiated) return;

        sceneController = new SceneController(window);
    }

    public void handleLoginButton(){
        try{
            System.out.println(loginField.getText());
            DbEngine.login(loginField.getText(), passwordField.getText());
            try{
                initiate();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                root = loader.load();
                Menu controller = loader.getController();
                controller.setUpcoming();
                controller.setSearchField(loginField.getText());
                Scene temp = new Scene(root, 600, 400);
                sceneController.changeScene(temp);
                window.show();
                window.setTitle("Logged in as " + loginField.getText());
                loginButton.getScene().getWindow().hide();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        } catch (LoginException e){
            loginField.clear();
            passwordField.clear();
            loginLabel.setVisible(true);
            loginLabel.setText("Incorrect login or password. Try again.");
        }
    }

    public void handleRegisterButton(){
        try {
            window = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
            root = loader.load();
            root.setOnKeyPressed(event -> {
                if(event.getCode() == KeyCode.ENTER){
                    Registration cont = loader.getController();
                    cont.handleConfirmButton();
                }
            });
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Registration");
            window.setScene(new Scene(root, 300, 325));
            window.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
