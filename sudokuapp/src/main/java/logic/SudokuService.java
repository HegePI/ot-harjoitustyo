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
    static UserSudokuDao UserSudokuDao;
    static Scanner lukija;
    static User loggedIn;

    public SudokuService() throws ClassNotFoundException, SQLException {
        db = new Database();
        users = new Userdao(db);
        sudokus = new Sudokudao(db);
        UserSudokuDao = new UserSudokuDao(db);
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
        ArrayList<Sudoku> allSudokus = this.sudokus.getAll();
        return allSudokus;
    }

    public UserSudoku play(Sudoku s, int userId) throws SQLException {
        UserSudoku us = new UserSudoku(s.getSudoku(), s.getSudoku());
        us.setId(s.getSudokuId());
        us.setUserId(userId);
        us.setDifficulty(s.getDifficulty());
        UserSudokuDao.add(us);
        UserSudoku us2 = UserSudokuDao.getByIds(s.getSudokuId(), userId);
        return us2;
    }

    public boolean save(UserSudoku us) {
        boolean succes = UserSudokuDao.save(us);
        return succes;
    }

    public User getUserById(int id) throws SQLException {
        User user = users.getById(id);
        return user;
    }
}
