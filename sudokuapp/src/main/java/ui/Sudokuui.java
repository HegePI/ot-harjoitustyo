package ui;

import java.awt.GridLayout;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.SudokuService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import logic.Sudoku;
import logic.User;

public class Sudokuui extends Application {

    private SudokuService service;
    private User loggedUser;
    private Stage window;
    private Notification notification;
    private Loginui loginView;
    private NewUserui newUserView;
    private Gameui gameView;
    private Menuui menuView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws ClassNotFoundException, SQLException {
        service = new SudokuService();
        notification = new Notification();
        loginView = new Loginui();
        newUserView = new NewUserui();
        gameView = new Gameui();
        menuView = new Menuui();
    }

    @Override
    public void start(Stage window) throws Exception {
        this.window = window;

        if (loggedUser == null) {
            setUpLoginView();
        } else {
            setUpMenuView();
        }
        window.show();
    }

    private void setUpLoginView() {
        window.setScene(loginView.getScene());
        window.setTitle("login-ikkuna");
        TextField name = setLoginNameField(loginView.getGridPane());
        PasswordField pswd = setLoginPasswordField(loginView.getGridPane());
        setLoginButton(loginView.getGridPane(), name, pswd);
        setCreateUserButton(loginView.getGridPane());

    }

    private TextField setLoginNameField(GridPane loginForm) {
        Label name = new Label("Käyttäjänimi: ");
        loginForm.add(name, 0, 1);
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        loginForm.add(nameField, 1, 1);
        return nameField;
    }

    private PasswordField setLoginPasswordField(GridPane loginForm) {
        Label password = new Label("Salasana: ");
        loginForm.add(password, 0, 2);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        loginForm.add(passwordField, 1, 2);

        return passwordField;

    }

    private void setLoginButton(GridPane gridPane, TextField name, PasswordField pswd) {
        Button login = new Button("Kirjaudu sisään");
        login.setPrefSize(160, 40);
        login.setOnAction(e -> {
            try {
                if (name.getText().isEmpty() || pswd.getText().isEmpty()) {
                    String message = "Anna sekä käyttäjänimi että salasana";
                    notification.Message(message);
                } else {
                    loggedUser = service.Login(name.getText(), pswd.getText());
                    setUpMenuView();
                }
            } catch (SQLException ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        });

        loginView.getGridPane().add(login, 0, 4, 2, 1);
        loginView.getGridPane().setHalignment(login, HPos.CENTER);
        loginView.getGridPane().setMargin(login, new Insets(20, 0, 20, 0));
    }

    private void setCreateUserButton(GridPane gridPane) {
        Button newUser = new Button("Luo uusi käyttäjä");
        newUser.setPrefSize(160, 40);
        newUser.setOnAction(e -> {
            setUpNewUserView();
        });

        loginView.getGridPane().add(newUser, 0, 5, 2, 1);
        loginView.getGridPane().setHalignment(newUser, HPos.CENTER);
        loginView.getGridPane().setMargin(newUser, new Insets(20, 0, 20, 0));

    }

    private void setUpNewUserView() {
        window.setScene(newUserView.getScene());
        window.setTitle("Luo uusi käyttäjä");

        TextField name = userNameField(newUserView.getGridPane());
        TextField pswd = userPasswordField(newUserView.getGridPane());
        createUserButton(newUserView.getGridPane(), name, pswd);
        newBackButton(newUserView.getGridPane());
    }

    private TextField userNameField(GridPane newUserForm) {
        Label name = new Label("Käyttäjänimi: ");
        newUserForm.add(name, 0, 1);

        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        newUserForm.add(nameField, 1, 1);

        return nameField;
    }

    private TextField userPasswordField(GridPane newUserForm) {
        Label password = new Label("Käyttäjänimi: ");
        newUserForm.add(password, 0, 2);

        TextField passwordField = new TextField();
        passwordField.setPrefHeight(40);
        newUserForm.add(passwordField, 1, 2);
        return passwordField;
    }

    private void createUserButton(GridPane gridPane, TextField name, TextField pswd) {
        Button createUser = new Button("Luo käyttäjä");
        createUser.setPrefSize(200, 40);

        createUser.setOnAction(e -> {
            try {
                service.CreateNewUser(name.getText(), pswd.getText());
                loggedUser = service.Login(name.getText(), pswd.getText());
                setUpMenuView();
            } catch (SQLException ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        });
        gridPane.add(createUser, 0, 3, 2, 1);
        gridPane.setHalignment(createUser, HPos.CENTER);
        gridPane.setMargin(createUser, new Insets(20, 0, 20, 0));
    }

    private void newBackButton(GridPane gridPane) {
        Button back = new Button("takaisin login -ikkunaan");
        back.setPrefSize(200, 40);

        back.setOnAction(e -> {
            setUpLoginView();
        });
        gridPane.add(back, 0, 4, 2, 1);
        gridPane.setHalignment(back, HPos.CENTER);
        gridPane.setMargin(back, new Insets(20, 0, 20, 0));
    }

    private void setGameView() {
        window.setScene(gameView.getScene());
        window.setTitle("Pelinäkymä");
    }

    private void setUpMenuView() throws SQLException {
        window.setScene(menuView.getScene());
        window.setTitle("Pelivalikko");
        setMenuBar(menuView.getBorderPane());
        listSudokusInMenu();
    }

    private void setMenuBar(BorderPane borderPane) {
        MenuBar menu = new MenuBar();
        setInfoMenu(menu);
        borderPane.setTop(menu);

    }

    private void setInfoMenu(MenuBar menu) {
        Menu info = new Menu();
        info.setText(loggedUser.getUserName() + " logged in");

        MenuItem logout = new MenuItem("Kirjaudu ulos");
        logout.setOnAction(e -> {
            loggedUser = null;
            setUpLoginView();
        });

        MenuItem personalSudokus = new MenuItem("Keskeneräiset sudokut");
        personalSudokus.setOnAction(e -> {
            setGameView();
        });

        info.getItems().addAll(logout, personalSudokus);
        menu.getMenus().add(info);
    }

    private void listSudokusInMenu() throws SQLException {
        System.out.println("Luodaan sudokut menuun");
        ArrayList<Sudoku> allSudokus = service.getAllSudokus();
        for (int i = 0; i < allSudokus.size(); i++) {
            sudokuInMenu(allSudokus.get(i), i);
        }

    }

    private void sudokuInMenu(Sudoku s, int row) {
        System.out.println(s.toString());
        Label sudoku = new Label(s.getId() + ", " + s.getDifficulty());
        Button play = new Button("pelaa");
        play.setOnAction(e -> {
            setGameView();
        });
        menuView.getGridPane().add(sudoku, 1, row + 1);
        menuView.getGridPane().add(play, 2, row + 1);

    }

}
