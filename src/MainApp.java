import controller.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class MainApp extends Application {

    Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("controller/Login.fxml"));
        Parent root = loader.load();
        root.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                Login cont = loader.getController();
                cont.handleLoginButton();
            }
        });
        stage.setTitle("MainApp");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
