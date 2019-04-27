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
import logic.SudokuService;
import logic.User;
import logic.UserSudoku;

public class UserInfoController implements Initializable {

    private final SudokuService server;
    private User loggedUser;
    @FXML
    private Label userName, userSudokus;
    @FXML
    private ListView usersSudokusView;
    @FXML
    private Button back;

    ObservableList sudokus;

    public UserInfoController() throws ClassNotFoundException, SQLException {
        this.server = new SudokuService();
        this.usersSudokusView = new ListView();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setUser(User user) {
        System.out.println(user.toString());
        this.loggedUser = user;
        userName.setText("Käyttäjän " + user.getUserName() + " tiedot");
        userSudokus.setText("Käyttäjän " + user.getUserName() + " sudokut");
        try {
            refreshView();
        } catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshView() throws SQLException {
        sudokus = FXCollections.observableArrayList();
        getUsersSudokus(sudokus);
        if (!sudokus.isEmpty()) {
            usersSudokusView.setItems(sudokus);
            usersSudokusView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    UserSudoku s = (UserSudoku) usersSudokusView.getSelectionModel().getSelectedItem();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxml/game.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    GameController c = loader.getController();
                    c.setUserSudoku(s);
                    Parent root = loader.getRoot();
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene ss = new Scene(root);
                    window.setTitle("menu");
                    window.setScene(ss);
                    window.show();
                }
            });
        }

    }

    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/menu.fxml"));
        loader.load();

        MenuController menu = loader.getController();
        menu.setUser(loggedUser);

        Parent root = loader.getRoot();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene s = new Scene(root);
        window.setTitle("menu");
        window.setScene(s);
        window.show();

    }

    private void getUsersSudokus(ObservableList sudokus) throws SQLException {
        ArrayList<UserSudoku> allSudokus = server.getAllUsersSudokus(loggedUser.getId());
        if (allSudokus.isEmpty()) {
            userSudokus.setText("Ei vielä yhtään aloitettua sudoku peliä");
        } else {
            System.out.println(allSudokus.size());
            sudokus.addAll(allSudokus);
        }
    }

}
