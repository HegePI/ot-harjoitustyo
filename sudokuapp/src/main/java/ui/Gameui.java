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

public class Gameui {

    private GridPane gameLayout;

    public Scene getScene() {

        gameLayout = gameView();
        Scene gameView = new Scene(gameLayout, 800, 800);

        return gameView;
    }

    public GridPane getGridPane() {
        return gameLayout;
    }

    private GridPane gameView() {
        GridPane gameView = new GridPane();
        gameView.setAlignment(Pos.CENTER);
        gameView.setPadding(new Insets(20, 20, 20, 20));
        gameView.setHgap(30);
        gameView.setVgap(30);
        ColumnConstraints cc1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        cc1.setHalignment(HPos.RIGHT);
        ColumnConstraints cc2 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        cc2.setHgrow(Priority.ALWAYS);
        gameView.getColumnConstraints().addAll(cc1, cc2);
        setGameViewHeader(gameView);
        return gameView;
    }

    private void setGameViewHeader(GridPane gameView) {
        Label header = new Label("Pelinäkymä");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gameView.add(header, 0, 0, 2, 1);
        gameView.setHalignment(header, HPos.CENTER);
        gameView.setMargin(header, new Insets(20, 0, 20, 0));
    }

}
