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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.SudokuService;

public class CreateUserController implements Initializable {

    private final SudokuService server;

    @FXML
    private TextField userName, password;
    @FXML
    private Button create, back;
    @FXML
    private Label info;

    public CreateUserController() throws ClassNotFoundException, SQLException {
        this.server = new SudokuService();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Kutsuu SudokuService -oliota ja luo uuden käyttäjän. Jos ei onnistunut,
     * ilmoitetaan tästä käyttäjälle.
     *
     * @throws SQLException Saattaa palauttaa SQLExceptionin, jos ei saatu
     * yhteyttä tietokantaan tai jos tietokantaa ei ollut olemassa.
     */
    public void createUser() throws SQLException {
        System.out.println("Uusi käyttäjä");
        if (userName.getText().isEmpty() || password.getText().isEmpty()) {
            userName.setText("");
            password.setText("");
            info.setText("Anna sekä käyttäjänimi että salasana");
        } else {
            boolean success = server.CreateNewUser(userName.getText(), password.getText());
            userName.setText("");
            password.setText("");
            if (success) {
                info.setText("luotiin uusi käyttäjä");
            } else {
                info.setText("jokin meni pieleen");
            }
        }
    }

    /**
     * Vaihtaa näkymän takaisin sisäänkirjautumisnäkymäksi.
     *
     * @param event Napin painamis tapahtuma
     * @throws IOException Saattaa palauttaa IOExceptionin, jos login.fxml sivua
     * ei saatu ladattua.
     */
    public void backToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene s = new Scene(root);
        window.setTitle("login");
        window.setScene(s);
        window.show();
    }
}
