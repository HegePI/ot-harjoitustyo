package ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Loginui {

    GridPane loginForm;

    public Scene getScene() {

        loginForm = loginForm();
        Scene loginScene = new Scene(loginForm, 800, 800);
        return loginScene;
    }

    private GridPane loginForm() {
        GridPane loginForm = new GridPane();
        loginForm.setAlignment(Pos.CENTER);
        loginForm.setPadding(new Insets(20, 20, 20, 20));
        loginForm.setHgap(30);
        loginForm.setVgap(30);
        ColumnConstraints cc1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        cc1.setHalignment(HPos.RIGHT);
        ColumnConstraints cc2 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        cc2.setHgrow(Priority.ALWAYS);
        loginForm.getColumnConstraints().addAll(cc1, cc2);
        setLoginHeader(loginForm);
        return loginForm;
    }

    private void setLoginHeader(GridPane loginForm) {
        Label header = new Label("Sudokuapp");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        loginForm.add(header, 0, 0, 2, 1);
        loginForm.setHalignment(header, HPos.CENTER);
        loginForm.setMargin(header, new Insets(20, 0, 20, 0));
    }

    public GridPane getGridPane() {
        return loginForm;
    }

}
