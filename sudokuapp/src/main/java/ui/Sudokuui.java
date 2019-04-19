package ui;

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
import logic.User;

public class Sudokuui extends Application {

    private logic.SudokuService service;
    private User loggedUser;
    private Stage window;
    private Notification notification;
    private Loginui loginView;
    private NewUserui newUserView;
    private Gameui gameView;
    private Menuui menuView;

    @Override
    public void init() throws ClassNotFoundException {
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

        window.setTitle("login -ikkuna");

        GridPane loginForm = loginForm();
        GridPane newUserForm = newUserForm();

        Scene loginScene = new Scene(loginForm, 800, 800);
        Scene createUserScene = new Scene(newUserForm, 800, 800);

        loginUiControls(loginForm);
        newUserUiControls(newUserForm);

        Button newUser = new Button("Luo uusi käyttäjä");
        newUser.setPrefHeight(40);
        newUser.setPrefWidth(140);
        loginForm.setHalignment(newUser, HPos.CENTER);
        loginForm.setMargin(newUser, new Insets(20, 0, 20, 0));
        newUser.setOnAction(e -> window.setScene(createUserScene));
        loginForm.add(newUser, 0, 5, 2, 1);

        Button back = new Button("takaisin");
        back.setPrefHeight(40);
        back.setPrefWidth(100);
        newUserForm.setHalignment(newUser, HPos.CENTER);
        newUserForm.setMargin(newUser, new Insets(20, 0, 20, 0));
        back.setOnAction(e -> window.setScene(loginScene));
        newUserForm.add(back, 0, 4, 2, 1);

        window.setScene(loginScene);
        window.show();
    }

    public static GridPane loginForm() {
        GridPane loginForm = new GridPane();
        loginForm.setAlignment(Pos.CENTER);
        loginForm.setPadding(new Insets(20, 20, 20, 20));
        loginForm.setHgap(30);
        loginForm.setVgap(30);
        ColumnConstraints cc1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        cc1.setHalignment(HPos.RIGHT);
        ColumnConstraints cc2 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        cc2.setHgrow(Priority.ALWAYS);
        loginForm.getColumnConstraints().addAll(cc1, cc2);
        return loginForm;
    }

    public void loginUiControls(GridPane loginForm) {
        setLoginHeader(loginForm);
        TextField name = setLoginNameField(loginForm);
        PasswordField pswd = setLoginPasswordField(loginForm);

        Button login = new Button("Kirjaudu sisään");
        login.setPrefSize(160, 40);
        login.setOnAction(e -> {
            try {
                if (name.getText().isEmpty() || pswd.getText().isEmpty()) {
                    String message = "Anna sekä käyttäjänimi että salasana";
                    notification.Message(message);
                } else {
                    loggedUser = service.Login(name.getText(), pswd.getText());
                    System.out.println("Käyttäjä " + loggedUser.toString() + " kirjautui sisään");
                    window.setScene(gameView.getScene());
                }
            } catch (SQLException ex) {
                Logger.getLogger(Sudokuui.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        loginForm.add(login, 0, 4, 2, 1);
        loginForm.setHalignment(login, HPos.CENTER);
        loginForm.setMargin(login, new Insets(20, 0, 20, 0));
    }

    private void setLoginHeader(GridPane gp) {
        Label header = new Label("Sudokuapp");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gp.add(header, 0, 0, 2, 1);
        gp.setHalignment(header, HPos.CENTER);
        gp.setMargin(header, new Insets(20, 0, 20, 0));
    }

    private TextField setLoginNameField(GridPane gp) {
        Label name = new Label("Käyttäjänimi: ");
        gp.add(name, 0, 1);

        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gp.add(nameField, 1, 1);

        return nameField;
    }

    private PasswordField setLoginPasswordField(GridPane gp) {
        Label password = new Label("Salasana: ");
        gp.add(password, 0, 2);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gp.add(passwordField, 1, 2);

        return passwordField;
    }

    private GridPane newUserForm() {
        GridPane newUserForm = new GridPane();
        newUserForm.setAlignment(Pos.CENTER);
        newUserForm.setPadding(new Insets(40, 40, 40, 40));
        newUserForm.setHgap(40);
        newUserForm.setVgap(40);
        ColumnConstraints cc1 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        cc1.setHalignment(HPos.RIGHT);
        ColumnConstraints cc2 = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        cc2.setHgrow(Priority.ALWAYS);
        newUserForm.getColumnConstraints().addAll(cc1, cc2);
        return newUserForm;
    }

    private void newUserUiControls(GridPane newUserForm) {
        newUserLabel(newUserForm);
        TextField name = newUserNameField(newUserForm);
        TextField pswd = newUserPasswordField(newUserForm);
        Button create = new Button("Luo käyttäjä");
        create.setPrefSize(200, 40);

        create.setOnAction(e -> {
            try {
                service.CreateNewUser(name.getText(), pswd.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Sudokuui.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        newUserForm.add(create, 0, 3, 2, 1);
        newUserForm.setHalignment(create, HPos.CENTER);
        newUserForm.setMargin(create, new Insets(20, 0, 20, 0));
    }

    private void newUserLabel(GridPane newUserForm) {
        Label header = new Label("Luo uusi käyttäjä");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        newUserForm.add(header, 0, 0, 2, 1);
        newUserForm.setHalignment(header, HPos.CENTER);
        newUserForm.setMargin(header, new Insets(20, 0, 20, 0));

    }

    private TextField newUserNameField(GridPane newUserForm) {
        Label name = new Label("Käyttäjänimi: ");
        newUserForm.add(name, 0, 1);

        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        newUserForm.add(nameField, 1, 1);

        return nameField;
    }

    private TextField newUserPasswordField(GridPane newUserForm) {
        Label password = new Label("Käyttäjänimi: ");
        newUserForm.add(password, 0, 2);

        TextField passwordField = new TextField();
        passwordField.setPrefHeight(40);
        newUserForm.add(passwordField, 1, 2);
        return passwordField;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
