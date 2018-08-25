package sample;

/**
 * Created by kopcion on 24.08.18.
 */
public class TicketDetails {
    public String name;
    public String type;
    public String date;
    public int price;

    public TicketDetails(String name, String type, String date, int price){
        this.name = name;
        this.type = type;
        this.date = date;
        this.price = price;
    }


    public String toString(){
        return name;
    }
}
