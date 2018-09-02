package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    Stage stage;

    public SceneController(Stage stage){
        this.stage = stage;
    }

    protected void changeScene(Scene scene){
        stage.setScene(scene);
    }
}
