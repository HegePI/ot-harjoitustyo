package ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.Sudoku;
import logic.SudokuService;
import logic.User;

/**
 * FXML Controller class
 *
 * @author heikki
 */
public class MenuController implements Initializable {

    private final SudokuService server;
    private User loggedUser;

    @FXML
    private Menu User;
    @FXML
    private ListView sudokuView;
    @FXML
    private Button logout;

    ObservableList sudokus;

    public MenuController() throws ClassNotFoundException, SQLException {
        this.server = new SudokuService();
    }

    public void SetUser(User u) {
        System.out.println(u.toString());
        this.loggedUser = u;
        User.setText(loggedUser.getUserName() + " logged in");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            refreshView();
        } catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logout(ActionEvent event) throws IOException {
        loggedUser = null;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene s = new Scene(root);
        window.setTitle("login");
        window.setScene(s);
        window.show();

    }

    private void refreshView() throws SQLException {
        sudokus = FXCollections.observableArrayList();
        getSudokus(sudokus);
        sudokuView.setItems(sudokus);
        sudokuView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                System.out.println(observable.toString() + " selected");

            }
        });
        sudokuView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Sudoku s = (Sudoku) sudokuView.getSelectionModel().getSelectedItem();
                System.out.println("Painettiin " + s.toString());

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/game.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
                GameController c = loader.getController();
                c.setSudoku(s);
                Parent root = loader.getRoot();
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene ss = new Scene(root);
                window.setTitle("menu");
                window.setScene(ss);
                window.show();

            }
        });

    }

    private void getSudokus(ObservableList sudokus) throws SQLException {
        ArrayList<Sudoku> allSudokus = server.getAllSudokus();
        sudokus.addAll(allSudokus);

    }

}
