package logic;

import dao.Database;
import logic.User;
import logic.Sudoku;
import dao.Sudokudao;
import dao.Userdao;
import java.sql.*;
import java.util.*;

public class SudokuService {

    static Database db;
    static Userdao users;
    static Sudokudao sudokus;
    static Scanner lukija;
    static User loggedIn;

    public SudokuService() throws ClassNotFoundException {
        db = new Database();
        users = new Userdao(db);
        sudokus = new Sudokudao(db);
        lukija = new Scanner(System.in);
    }

    public void testUsers() throws SQLException {
        users.addUser(new User("teemu", "pulli"));
        users.addUser(new User("ville", "pulli"));
        users.addUser(new User("heikki", "pulli"));
        users.addUser(new User("eetu", "pulli"));

        User user = users.getById(3);
        System.out.println(user.toString());

        ArrayList<User> userList = users.getAll();
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i));
        }
        users.deleteById(2);
    }

    public void testSudokus() throws SQLException {
        int[][] test = {
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
        Sudoku s1 = new Sudoku(false, "easy", test);
        System.out.println(s1.toString());
        if (sudokus.addSudoku(s1)) {
            System.out.println("Lisättiin sudoku");
        } else {
            System.out.println("Epäonnistui");
        }

        Sudoku s2 = sudokus.getById(1);
        System.out.println(s2.toString());

        sudokus.deleteAll();
    }

}
