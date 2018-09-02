package Database;

import exceptions.LoginException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.TicketDetails;

import java.sql.*;

public class DbEngine {

    private static Connection connection;

    static {
        String url = "jdbc:postgresql://localhost/postgres";
        String user = "kopcion";
        String password = "kopcion";

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void login(String login, String password) throws LoginException {
        if(login.equals(null) || password.equals(null) || login.length() < 6 || password.length() < 6) throw new LoginException();
        if(!checkLogin(login)) {
            System.out.println("kappa");
            throw new LoginException();
        }
        if(!checkPassword(login,password)) throw new LoginException();
    }

    public static void register(String login, String password, String name, String surname){
        PreparedStatement stmt = null;
        try {
            int id = countRows("users");
            stmt = connection.prepareStatement("INSERT INTO users VALUES(?,?,?)");
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, surname);
            stmt.execute();
            stmt = connection.prepareStatement("INSERT INTO logins VALUES(?,?,?)");
            stmt.setInt(1, id);
            stmt.setString(2, login);
            stmt.setString(3, password);
            stmt.execute();
            System.out.println("registered");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int countRows(String name){
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet set = stmt.executeQuery("SELECT count(*) from " + name);
            set.next();
            return set.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static int countRows(String name, String condition){
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet set = stmt.executeQuery("SELECT count(*) from " + name + " where " + condition + ";");
            set.next();
            return set.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static int getID(String username){
        Statement stmt;
        try {
            stmt = connection.createStatement();
            ResultSet set = stmt.executeQuery("SELECT id FROM logins where username = '" + username + "'");
            System.out.println(set.getFetchSize());
            set.next();
            return set.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    static boolean checkLogin(String login){
        Statement statement = null;
        ResultSet set = null;
        try {
            statement = connection.createStatement();
            set = statement.executeQuery("SELECT count(*) FROM logins WHERE username = '" + login + "';");
            set.next();
            return(set.getInt(1) == 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    static boolean checkPassword(String login, String password){
        try {
            Statement stmt = connection.createStatement();
            ResultSet set = stmt.executeQuery("SELECT password FROM logins WHERE username = '" + login + "';");
            set.next();
            return password.equals(set.getString(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ObservableList getTickets(String user, String search){
        System.out.println("lol");
        ObservableList lol = FXCollections.observableArrayList();
        int number;
        ResultSet set;
        try {
            Statement statement = connection.createStatement();
            System.out.println(user);
            set = statement.executeQuery("select countTickets('" + user + "');");
            set.next();
            number = set.getInt(1);
            System.out.println(number);
            set = statement.executeQuery("SELECT * FROM getTickets('" + user + "');" );
            set.next();
            for(int i=0; i < number; i++){
                lol.add(new TicketDetails(set.getString(1), set.getString(2), set.getString(3), set.getInt(4)));
                set.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lol;
    }

    public static TicketDetails getTicketDetails(String ticketID) {
        TicketDetails details = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT EVENTS.Name, TICKET_KINDS.Kind, EVENTS.Date, EVENT_Types.price from TICKETS join EVENTS ON TICKETS.Event_ID = EVENTS.ID join event_types on EVENTS.Type = EVENT_Types.type where TICKETS.ID = " + ticketID);
            set.next();
            details = new TicketDetails(set.getString(1), set.getString(2), set.getString(3), set.getInt(4));
        } catch (Exception e){
            e.printStackTrace();
        }
        return details;
    }

    public static ObservableList getUpcomingEvents(){
        ObservableList list = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM upcoming;");
            int number = countRows("events", "date > now()");
            for(int i=0; i < number; i++){
                set.next();
                list.add(set.getString(1) + ", " + set.getString(2));
                System.out.println(set.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}