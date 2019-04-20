package ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Menuui {

    BorderPane menuBorders;
    GridPane menuLayout;

    public Scene getScene() {

        menuBorders = new BorderPane();
        menuLayout = menuView();
        menuBorders.setCenter(menuLayout);
        Scene menuView = new Scene(menuBorders, 800, 800);

        return menuView;
    }

    public GridPane getGridPane() {
        return menuLayout;
    }

    public BorderPane getBorderPane() {
        return menuBorders;
    }

    private GridPane menuView() {
        GridPane menuView = new GridPane();
        menuView.setAlignment(Pos.CENTER);
        menuView.setPadding(new Insets(20, 20, 20, 20));
        menuView.setHgap(30);
        menuView.setVgap(30);
        ColumnConstraints cc1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        cc1.setHalignment(HPos.RIGHT);
        ColumnConstraints cc2 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        cc2.setHgrow(Priority.ALWAYS);
        menuView.getColumnConstraints().addAll(cc1, cc2);
        setMenuViewHeader(menuView);
        return menuView;
    }

    private void setMenuViewHeader(GridPane menuView) {
        Label header = new Label("Pelivalikko");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        menuView.add(header, 0, 0, 2, 1);
        menuView.setHalignment(header, HPos.CENTER);
        menuView.setMargin(header, new Insets(20, 0, 20, 0));
    }
}
