package controller;


import Database.DbEngine;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.TicketDetails;
import sun.util.calendar.BaseCalendar;
import utils.FXUtils;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu {

    private String login;
    public Button confirmButton;
    public ListView searchResults;
    public TextField searchField;
    public ListView upcoming;

    public Menu(){}
    public void handleConfirmButton(){
        login = ((Stage)confirmButton.getScene().getWindow()).getTitle();
        login = login.substring(13);
        searchResults.setItems(DbEngine.getTickets(login, searchField.getText()));
    }

    public void setUpcoming(){
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
        upcoming.setItems(DbEngine.getUpcomingEvents());
    }
    public void handleSearchField(){
        TicketDetails details = (TicketDetails)searchResults.getSelectionModel().getSelectedItems().get(0);
        try {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Registration");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketDetails.fxml"));
            Parent root = loader.load();
            root.setOnKeyPressed(event -> {
                if(event.getCode() == KeyCode.ENTER){
                    Details cont = loader.getController();
                    cont.handleOkButton();
                }
            });
            Details controller = loader.getController();
            controller.setDetails(details);

            Scene scene = new Scene(root, 240, 160);

            window.setScene(scene);
            window.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSearchField(String username) {
        searchResults.setItems(DbEngine.getTickets(username , searchField.getText()));

    }
}
