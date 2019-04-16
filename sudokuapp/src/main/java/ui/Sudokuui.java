package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;
import logic.Sudoku;
import logic.SudokuService;
import dao.Sudokudao;
import dao.Database;

public class Sudokuui extends Application {

    private logic.SudokuService service;
    private Sudokudao sd;
    private Database db;

    @Override
    public void init() throws ClassNotFoundException {
        this.service = new SudokuService();
        this.db = new Database();
        this.sd = new dao.Sudokudao(db);
    }

    @Override
    public void start(Stage window) throws Exception {

        int[][] sa = {
            {0, 0, 0, 5, 0, 7, 0, 0, 0},
            {0, 0, 2, 4, 0, 6, 3, 0, 0},
            {0, 9, 0, 0, 1, 0, 0, 2, 0},
            {2, 7, 0, 0, 0, 0, 0, 6, 8},
            {0, 0, 3, 0, 0, 0, 1, 0, 0},
            {1, 4, 0, 0, 0, 0, 0, 9, 3},
            {0, 6, 0, 0, 4, 0, 0, 5, 0},
            {0, 0, 9, 2, 0, 5, 6, 0, 0},
            {0, 0, 0, 9, 0, 3, 0, 0, 0}
        };
        Sudoku s1 = new Sudoku(false, "easy", sa);
        sd.addSudoku(s1);
        Sudoku s2 = sd.getById(1);
        System.out.println(s2.toString());
        window.setTitle("login -ikkuna");

        GridPane loginForm = loginForm();
        GridPane newUserForm = newUserForm();
        loginUiControls(loginForm, window);
        newUserUiControls(newUserForm, window);
        Scene loginScene = new Scene(loginForm, 800, 800);
        Scene newUserScene = new Scene(newUserForm, 800, 800);
        window.setScene(newUserScene);
        window.show();
    }

    public GridPane loginForm() {
        GridPane loginForm = new GridPane();
        loginForm.setAlignment(Pos.CENTER);
        loginForm.setPadding(new Insets(40, 40, 40, 40));
        loginForm.setHgap(40);
        loginForm.setVgap(40);
        ColumnConstraints cc1 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        cc1.setHalignment(HPos.RIGHT);
        ColumnConstraints cc2 = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        cc2.setHgrow(Priority.ALWAYS);
        loginForm.getColumnConstraints().addAll(cc1, cc2);
        return loginForm;
    }

    public void loginUiControls(GridPane gp, Stage window) {
        setHeader(gp);
        setNameField(gp);
        setPasswordField(gp);
        setLoginButton(gp, window);
        setCreateUserButton(gp, window);
    }

    private void setHeader(GridPane gp) {
        Label header = new Label("Sudokuapp");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gp.add(header, 0, 0, 2, 1);
        gp.setHalignment(header, HPos.CENTER);
        gp.setMargin(header, new Insets(20, 0, 20, 0));
    }

    private void setNameField(GridPane gp) {
        Label name = new Label("Käyttäjänimi: ");
        gp.add(name, 0, 1);

        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gp.add(nameField, 1, 1);

    }

    private void setPasswordField(GridPane gp) {
        Label password = new Label("Salasana: ");
        gp.add(password, 0, 2);

        TextField passwordField = new TextField();
        passwordField.setPrefHeight(40);
        gp.add(passwordField, 1, 2);
    }

    private void setLoginButton(GridPane gp, Stage window) {
        Button submit = new Button("login");
        submit.setPrefHeight(40);
        submit.setDefaultButton(true);
        submit.setPrefWidth(100);
        gp.add(submit, 0, 4, 2, 1);
        gp.setHalignment(submit, HPos.CENTER);
        gp.setMargin(submit, new Insets(20, 0, 20, 0));
    }

    private void setCreateUserButton(GridPane gp, Stage window) {
        Button newUser = new Button("Luo uusi käyttäjä");
        newUser.setPrefHeight(40);
        newUser.setPrefWidth(200);
        gp.add(newUser, 0, 5, 2, 1);
        gp.setHalignment(newUser, HPos.CENTER);
        gp.setMargin(newUser, new Insets(20, 0, 20, 0));
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

    private void newUserUiControls(GridPane newUserForm, Stage window) {
        newUserLabel(newUserForm);
        newUserNameField(newUserForm);
        newUserPasswordField(newUserForm);
        newUserCreateButton(newUserForm);

    }

    private void newUserLabel(GridPane newUserForm) {
        Label header = new Label("Luo uusi käyttäjä");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        newUserForm.add(header, 0, 0, 2, 1);
        newUserForm.setHalignment(header, HPos.CENTER);
        newUserForm.setMargin(header, new Insets(20, 0, 20, 0));

    }

    private void newUserNameField(GridPane newUserForm) {
        Label name = new Label("Käyttäjänimi: ");
        newUserForm.add(name, 0, 1);

        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        newUserForm.add(nameField, 1, 1);
    }

    private void newUserPasswordField(GridPane newUserForm) {
        Label password = new Label("Käyttäjänimi: ");
        newUserForm.add(password, 0, 2);

        TextField passwordField = new TextField();
        passwordField.setPrefHeight(40);
        newUserForm.add(passwordField, 1, 2);
    }

    private void newUserCreateButton(GridPane newUserForm) {
        Button newUser = new Button("Luo uusi käyttäjä");
        newUser.setPrefHeight(40);
        newUser.setPrefWidth(200);
        newUserForm.add(newUser, 0, 3, 2, 1);
        newUserForm.setHalignment(newUser, HPos.CENTER);
        newUserForm.setMargin(newUser, new Insets(20, 0, 20, 0));
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
