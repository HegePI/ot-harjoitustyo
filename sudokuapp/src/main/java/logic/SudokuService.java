package logic;

import dao.Database;
import dao.Sudokudao;
import dao.UserSudokuDao;
import dao.Userdao;
import java.sql.*;
import java.util.*;

public class SudokuService {

    static Database db;
    static Userdao users;
    static Sudokudao sudokus;
    static UserSudokuDao usd;
    static Scanner lukija;
    static User loggedIn;

    public SudokuService() throws ClassNotFoundException, SQLException {
        db = new Database();
        users = new Userdao(db);
        sudokus = new Sudokudao(db);
        lukija = new Scanner(System.in);
    }

    public boolean CreateNewUser(String name, String pswd) throws SQLException {
        boolean succes = false;

        User newUser = new User(name, pswd);
        succes = users.addUser(newUser);

        return succes;

    }

    public User Login(String name, String pswd) throws SQLException {
        User user = users.getByNameAndPswd(name, pswd);
        return user;
    }

    public Sudoku getSudoku(int id) {
        Sudoku sudoku = sudokus.getById(id);
        return sudoku;
    }

    public ArrayList<Sudoku> getAllSudokus() throws SQLException {
        ArrayList<Sudoku> sudokus = this.sudokus.getAll();
        return sudokus;
    }

    public void play(Sudoku s, int userId) throws SQLException {
        UserSudoku us = new UserSudoku(s.getSudoku(), s.getSudoku());
        usd.add(us);
    }
}
