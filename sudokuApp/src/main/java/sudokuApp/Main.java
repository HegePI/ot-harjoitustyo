package sudokuApp;

import java.sql.*;
import java.util.*;

public class Main {

    static Database db;
    static userDao users;
    static sudokuDao sudokus;
    static Scanner lukija;
    static user loggedIn;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        db = new Database();
        users = new userDao(db);
        sudokus = new sudokuDao(db);
        lukija = new Scanner(System.in);

        testUsers();
        testSudokus();

    }

    private static void testUsers() throws SQLException {
        users.addUser(new user("teemu", "pulli"));
        users.addUser(new user("ville", "pulli"));
        users.addUser(new user("heikki", "pulli"));
        users.addUser(new user("eetu", "pulli"));

        user user = users.getById(3);
        System.out.println(user.toString());

        ArrayList<user> userList = users.getAll();
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i));
        }

        users.deleteById(2);

    }

    private static void testSudokus() throws SQLException {
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
        sudoku s1 = new sudoku(false, "easy", test);
        System.out.println(s1.toString());
        if (sudokus.addSudoku(s1)) {
            System.out.println("Lisättiin sudoku");
        } else {
            System.out.println("Epäonnistui");
        }

        sudoku s2 = sudokus.getById(1);
        System.out.println(s2.toString());

        sudokus.deleteAll();
    }
}
