package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Loginui {

    private static GridPane loginForm = new GridPane();
    private static Scene loginScene = new Scene(loginForm);

    public Scene getScene(Button login) {

        return loginScene;
    }

}
