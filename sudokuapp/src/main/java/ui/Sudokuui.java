package ui;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.SudokuService;
import logic.User;

public class Sudokuui extends Application {

    private SudokuService service;
    private User loggedUser;
    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws ClassNotFoundException, SQLException {
        service = new SudokuService();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        String css = Sudokuui.class.getResource("/styles/login.css").toExternalForm();
        Scene s = new Scene(root);
        s.getStylesheets().add(css);
        window.setTitle("login");
        window.setScene(s);
        window.show();
    }
}
