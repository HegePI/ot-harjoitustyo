package ui;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Notification extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void Message(String message) {
        Stage notification = new Stage();
        notification.initModality(Modality.APPLICATION_MODAL);
        notification.setTitle("Huomio!");

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setHgap(30);
        layout.setVgap(30);

        Label alert = new Label("Huomio!");
        alert.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        alert.setPrefSize(200, 200);

        Label message2 = new Label(message);

        layout.add(alert, 1, 1);
        layout.add(message2, 1, 2);
        layout.setHalignment(alert, HPos.CENTER);
        layout.setMargin(alert, new Insets(20, 0, 20, 0));
        layout.setHalignment(message2, HPos.CENTER);
        layout.setMargin(message2, new Insets(20, 0, 20, 0));

        Scene notifScene = new Scene(layout, 400, 400);
        notification.setScene(notifScene);

        notification.showAndWait();
    }

}
