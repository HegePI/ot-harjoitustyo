package logic;

import dao.Database;
import dao.SudokuDao;
import dao.UserSudokuDao;
import dao.UserDao;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SudokuServiceTest {

    private final Database db;
    private final UserDao users;
    private final SudokuDao sudokus;
    private final UserSudokuDao UserSudokuDao;
    private final SudokuService server;

    public SudokuServiceTest() throws ClassNotFoundException, SQLException {
        this.db = new Database();
        this.UserSudokuDao = new UserSudokuDao(db);
        this.users = new UserDao(db);
        this.sudokus = new SudokuDao(db);
        this.server = new SudokuService();
    }

    @Before
    public void setUp() throws SQLException {
        users.deleteAll();
        sudokus.deleteAll();
        UserSudokuDao.deleteAll();
    }

    @Test
    public void createNewUser() throws SQLException {
        String name = "A";
        String pswd = "A";
        server.createNewUser(name, pswd);

        User user = users.getById(1);

        Boolean succes = false;
        if (user.getUserName().equals("A") && user.getPswd().equals("A")) {
            succes = true;
        }
        assertEquals(true, succes);
    }

    @Test
    public void login() throws SQLException {
        String name = "A";
        String pswd = "A";
        server.createNewUser(name, pswd);

        User user = server.login(name, pswd);

        boolean succes = false;

        if (user.getUserName().equals(name) && user.getPswd().equals(pswd)) {
            succes = true;
        }
        assertEquals(true, succes);
    }

    @Test
    public void getSudoku() throws SQLException {
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
        Sudoku s1 = new Sudoku("easy", sa);
        sudokus.addSudoku(s1);
        Sudoku s = server.getSudoku(1);

        boolean succes = false;
        if (s.equals(sa)) {
            succes = true;
        }
    }

    @Test
    public void getAllSudokus() throws SQLException {
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
        Sudoku s1 = new Sudoku("easy", sa);
        Sudoku s2 = new Sudoku("normal", sa);
        Sudoku s3 = new Sudoku("hard", sa);
        Sudoku s4 = new Sudoku("extreme", sa);

        sudokus.addSudoku(s1);
        sudokus.addSudoku(s2);
        sudokus.addSudoku(s3);
        sudokus.addSudoku(s4);

        ArrayList<Sudoku> allSudokus = server.getAllSudokus();

        assertEquals(4, allSudokus.size());

    }

    @Test
    public void getAllUsersSudokus() throws SQLException {
        User user = new User("A", "A");
        users.addUser(user);

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
        int[][] sc = {
            {0, 0, 5, 3, 0, 0, 0, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 2, 0},
            {0, 7, 0, 0, 1, 0, 5, 0, 0},
            {4, 0, 0, 0, 0, 5, 3, 0, 0},
            {0, 1, 0, 0, 7, 0, 0, 0, 6},
            {0, 0, 3, 2, 0, 0, 0, 8, 0},
            {0, 6, 0, 5, 0, 0, 0, 0, 9},
            {0, 0, 4, 0, 0, 0, 0, 3, 0},
            {0, 0, 0, 0, 0, 9, 7, 0, 0}
        };
        Sudoku s1 = new Sudoku("easy", sa);
        Sudoku s2 = new Sudoku("medium", sc);
        sudokus.addSudoku(s1);
        sudokus.addSudoku(s2);

        UserSudoku us1 = new UserSudoku(sa, sa, 1, 1, "easy");
        UserSudoku us2 = new UserSudoku(sc, sc, 2, 1, "medium");

        UserSudokuDao.addUserSudoku(us1);
        UserSudokuDao.addUserSudoku(us2);

        ArrayList<UserSudoku> userSudokus = server.getAllUsersSudokus(1);

        assertEquals(2, userSudokus.size());

    }

    @Test
    public void play() throws SQLException {
        User user = new User("A", "A");
        users.addUser(user);

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
        Sudoku s1 = new Sudoku("easy", sa);
        sudokus.addSudoku(s1);
        Sudoku s2 = sudokus.getById(1);
        server.play(s2, 1);

        UserSudoku usd = UserSudokuDao.getByIds(1, 1);

        boolean succes = false;

        if (usd.sudokuToString().equals(s1.sudokuToString())) {
            if (usd.getSudokuId() == 1) {
                if (usd.getUserId() == 1) {
                    succes = true;
                }
            }
        }
        assertEquals(true, succes);
    }

    @Test
    public void save() throws SQLException {
        User user = new User("A", "A");
        users.addUser(user);

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
        Sudoku s1 = new Sudoku("easy", sa);
        sudokus.addSudoku(s1);

        UserSudoku us = new UserSudoku(sa, sa, 1, 1, "easy");
        boolean succes = server.save(us);

        assertEquals(true, succes);
    }

    @Test
    public void getUserById() throws SQLException {
        User user = new User("A", "A");
        users.addUser(user);

        User u = server.getUserById(1);

        boolean same = false;

        if (u.equals(user)) {
            same = true;
        }
        assertEquals(true, same);
    }

    @Test
    public void checkSudoku() throws SQLException {
        User user = new User("A", "A");
        users.addUser(user);

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
        int[][] solved = {
            {8, 3, 1, 5, 2, 7, 9, 4, 6},
            {7, 5, 2, 4, 9, 6, 3, 8, 1},
            {6, 9, 4, 3, 1, 8, 7, 2, 5},
            {2, 7, 5, 1, 3, 9, 4, 6, 8},
            {9, 8, 3, 6, 5, 4, 1, 7, 2},
            {1, 4, 6, 8, 7, 2, 5, 9, 3},
            {3, 6, 8, 7, 4, 1, 2, 5, 9},
            {4, 1, 9, 2, 8, 5, 6, 3, 7},
            {5, 2, 7, 9, 6, 3, 8, 1, 4}
        };
        Sudoku s1 = new Sudoku("easy", sa);
        UserSudoku us1 = new UserSudoku(solved, sa, 1, 1, "easy");
        boolean correct = server.checkSudoku(us1);

        assertEquals(true, correct);

    }

    @Test
    public void solveSudoku() {
        int[][] solved = {
            {8, 3, 1, 5, 2, 7, 9, 4, 6},
            {7, 5, 2, 4, 9, 6, 3, 8, 1},
            {6, 9, 4, 3, 1, 8, 7, 2, 5},
            {2, 7, 5, 1, 3, 9, 4, 6, 8},
            {9, 8, 3, 6, 5, 4, 1, 7, 2},
            {1, 4, 6, 8, 7, 2, 5, 9, 3},
            {3, 6, 8, 7, 4, 1, 2, 5, 9},
            {4, 1, 9, 2, 8, 5, 6, 3, 7},
            {5, 2, 7, 9, 6, 3, 8, 1, 4}
        };
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
        Sudoku s1 = new Sudoku("easy", sa);
        UserSudoku us1 = new UserSudoku(solved, sa, 1, 1, "easy");
        UserSudoku us2 = new UserSudoku(sa, sa, 1, 1, "easy");

        server.solveSudoku(us2);

        boolean same = false;

        if (us1.equals(us2)) {
            same = true;
        }

        assertEquals(true, same);
    }
}
