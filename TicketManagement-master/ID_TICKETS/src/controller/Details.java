package controller;

import javafx.scene.control.Label;
import sample.TicketDetails;

/**
 * Created by kopcion on 24.08.18.
 */
public class Details {
    public Label eventName, type, price, date;

    public void handleOkButton(){
        eventName.getScene().getWindow().hide();
    }


    public void setDetails(TicketDetails details) {
        date.setText(details.date);
        price.setText(Integer.toString(details.price));
        type.setText(details.type);
        eventName.setText(details.name);
    }
}
