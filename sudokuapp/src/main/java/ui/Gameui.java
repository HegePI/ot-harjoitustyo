package ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Gameui {

    BorderPane layout = new BorderPane();
    Scene gameView = new Scene(layout);

    public Scene getScene() {
        Label test = new Label("Pelinäkymä");
        layout.setCenter(test);

        return this.gameView;
    }

}
