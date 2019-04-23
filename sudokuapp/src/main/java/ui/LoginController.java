/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.SudokuService;
import logic.User;

/**
 * FXML Controller class
 *
 * @author heikki
 */
public class LoginController implements Initializable {

    private SudokuService server;

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Button newUser;
    @FXML
    private Label info;

    public LoginController() throws ClassNotFoundException, SQLException {
        this.server = new SudokuService();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void login(ActionEvent event) throws SQLException, IOException {
        if (userName.getText().isEmpty() || password.getText().isEmpty()) {
            userName.setText("");
            password.setText("");
            info.setText("Anna sekä käyttäjänimi että salasana");
        } else {
            User loggedUser = server.Login(userName.getText(), password.getText());
            if (loggedUser == null) {
                userName.setText("");
                password.setText("");
                info.setText("Ei löydetty haluttua käyttäjää");
            } else {
                System.out.println(loggedUser.toString() + "logged in");

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/menu.fxml"));
                loader.load();

                MenuController menu = loader.getController();
                menu.SetUser(loggedUser);

                Parent root = loader.getRoot();
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene s = new Scene(root);
                window.setTitle("menu");
                window.setScene(s);
                window.show();

            }
        }
    }

    public void createUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/createUser.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene s = new Scene(root);
        window.setTitle("Luo käyttäjä");
        window.setScene(s);
        window.show();

    }

}
