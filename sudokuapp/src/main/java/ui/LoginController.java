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

public class LoginController implements Initializable {

    private SudokuService server;

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Button login, newUser;
    @FXML
    private Label info;

    public LoginController() throws ClassNotFoundException, SQLException {
        this.server = new SudokuService();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Kirjaa käyttäjän sisään, jos tiedot löytyvät tietokannasta ja vaihtaa
     * näkymän pelivalikkoon.
     *
     * @param event Napin painamis tapatuma
     * @throws SQLException Saattaa palauttaa SQLExceptionin, jos ei saada
     * yhteyttä tietokantaan tai tietokantaa ei ole olemassa.
     * @throws IOException Saattaa palauttaa IOExceptionin, jos login.fxml sivua
     * ei saatu ladattua.
     */
    public void login(ActionEvent event) throws SQLException, IOException {
        if (userName.getText().isEmpty() || password.getText().isEmpty()) {
            userName.setText("");
            password.setText("");
            info.setText("Anna sekä käyttäjänimi että salasana");
        } else {
            User loggedUser = server.login(userName.getText(), password.getText());
            if (loggedUser == null) {
                userName.setText("");
                password.setText("");
                info.setText("Ei löydetty haluttua käyttäjää");
            } else {

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
        }
    }

    /**
     * Vaihtaa näkymän käyttäjän luomis näkymään.
     *
     * @param event Napin painamis tapahtuma
     * @throws IOException Saattaa palauttaa IOExceptionin, jos login.fxml sivua
     * ei saatu ladattua.
     */
    public void createUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/createUser.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene s = new Scene(root);
        window.setTitle("Luo käyttäjä");
        window.setScene(s);
        window.setTitle("Luo käyttäjä");
        window.show();

    }

}
