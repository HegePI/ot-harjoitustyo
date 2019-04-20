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

public class NewUserui {

    GridPane newUserForm;

    public Scene getScene() {

        newUserForm = newUserForm();
        Scene NewUserView = new Scene(newUserForm, 800, 800);
        return NewUserView;

    }

    public GridPane getGridPane() {
        return newUserForm;
    }

    private GridPane newUserForm() {
        GridPane createUserForm = new GridPane();
        createUserForm.setAlignment(Pos.CENTER);
        createUserForm.setPadding(new Insets(20, 20, 20, 20));
        createUserForm.setHgap(30);
        createUserForm.setVgap(30);
        ColumnConstraints cc1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        cc1.setHalignment(HPos.RIGHT);
        ColumnConstraints cc2 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        cc2.setHgrow(Priority.ALWAYS);
        createUserForm.getColumnConstraints().addAll(cc1, cc2);
        setNewUserHeader(createUserForm);
        return createUserForm;
    }

    private void setNewUserHeader(GridPane createUserForm) {
        Label header = new Label("Luo uusi käyttäjä");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        createUserForm.add(header, 0, 0, 2, 1);
        createUserForm.setHalignment(header, HPos.CENTER);
        createUserForm.setMargin(header, new Insets(20, 0, 20, 0));
    }

}
