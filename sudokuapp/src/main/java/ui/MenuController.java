package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.Sudoku;
import logic.SudokuService;
import logic.User;
import logic.UserSudoku;

public class MenuController implements Initializable {

    private final SudokuService server;
    private User loggedUser;

    @FXML
    private Label user;
    @FXML
    private ListView sudokuView;
    @FXML
    private Button logout, userInfo;

    ObservableList sudokus;

    public MenuController() throws ClassNotFoundException, SQLException {
        this.server = new SudokuService();
    }

    public void setUser(User u) {
        this.loggedUser = u;
        user.setText(loggedUser.getUserName() + " logged in");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            refreshView();
        } catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logout(ActionEvent event) throws IOException {
        loggedUser = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/login.fxml"));
        loader.load();

        Parent root = loader.getRoot();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene s = new Scene(root);
        window.setTitle("login");
        window.setScene(s);
        window.show();

    }

    public void userInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/userInfo.fxml"));
        loader.load();

        UserInfoController uc = loader.getController();
        uc.setUser(loggedUser);

        Parent root = loader.getRoot();
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
        sudokuView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Sudoku s = (Sudoku) sudokuView.getSelectionModel().getSelectedItem();
                UserSudoku us = null;
                try {
                    us = server.play(s, loggedUser.getId());
                } catch (SQLException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/game.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
                GameController c = loader.getController();
                c.setUserSudoku(us);
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
