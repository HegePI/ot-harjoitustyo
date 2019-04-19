package ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Menuui {

    BorderPane layout = new BorderPane();
    Scene menuView = new Scene(layout);

    public Scene getScene() {
        Label test = new Label("Menu näkymä");
        layout.setCenter(test);

        return this.menuView;
    }
}
