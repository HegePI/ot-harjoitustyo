package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import logic.User;
import logic.UserSudoku;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author heikki
 */
public class UserSudokuDaoTest {

    private Database db;
    private UserSudokuDao usd;
    private SudokuDao sd;
    private UserDao ud;

    public UserSudokuDaoTest() throws ClassNotFoundException, SQLException {
        this.db = new Database();
        this.usd = new UserSudokuDao(db);
        this.sd = new SudokuDao(db);
        this.ud = new UserDao(db);

    }

    @Before
    public void setUp() throws SQLException {
        usd.deleteAll();
    }

    @Test
    public void addUserSudoku() throws SQLException {
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
        UserSudoku us = new UserSudoku(sa, sa, 1, 1, "easy");

        boolean succes = usd.addUserSudoku(us);

        Assert.assertEquals(true, succes);
    }

    @Test
    public void getByIds() throws SQLException {
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
        UserSudoku us = new UserSudoku(sa, sa, 1, 1, "easy");

        boolean succes = usd.addUserSudoku(us);

        UserSudoku us2 = usd.getByIds(1, 1);

        boolean succes2 = false;
        if (us2.getUserId() == us.getUserId() && us2.getSudokuId() == us.getSudokuId()) {
            succes2 = true;
        }
        Assert.assertEquals(true, succes2);
    }

    @Test
    public void saveUserSudoku() throws SQLException {
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
        UserSudoku us = new UserSudoku(sa, sa, 1, 1, "easy");

        boolean succes = usd.addUserSudoku(us);

        us.setCell(0, 0, 1);

        usd.saveUserSudoku(us);

        UserSudoku us2 = usd.getByIds(1, 1);

        boolean succes2 = false;

        if (us2.getSudoku()[0][0] == 1) {
            succes2 = true;
        }
        Assert.assertEquals(true, succes2);
    }

    @Test
    public void deleteAll() throws SQLException {
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
        UserSudoku us = new UserSudoku(sa, sa, 1, 1, "easy");
        UserSudoku us1 = new UserSudoku(sa, sa, 2, 1, "easy");
        UserSudoku us2 = new UserSudoku(sa, sa, 3, 1, "easy");

        usd.addUserSudoku(us);
        usd.addUserSudoku(us1);
        usd.addUserSudoku(us2);

        boolean succes = usd.deleteAll();
        Assert.assertEquals(true, succes);
    }

    @Test
    public void getUsersSudokus() throws SQLException {
        User user1 = new User("helppo", "heikki");
        ud.addUser(user1);
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
        int[][] sb = {
            {0, 0, 0, 5, 4, 0, 0, 0, 8},
            {6, 0, 0, 0, 0, 2, 3, 0, 0},
            {0, 0, 7, 0, 0, 3, 0, 9, 0},
            {0, 3, 1, 0, 5, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 0, 0, 3, 0, 7, 1, 0},
            {0, 9, 0, 7, 0, 0, 2, 0, 0},
            {0, 0, 8, 6, 0, 0, 0, 0, 5},
            {1, 0, 0, 0, 2, 4, 0, 0, 0}
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
        UserSudoku us1 = new UserSudoku(sa, sa, 1, 1, "easy");
        UserSudoku us2 = new UserSudoku(sb, sb, 2, 1, "medium");
        UserSudoku us3 = new UserSudoku(sc, sc, 3, 1, "Maailman vaikein sudoku");

        usd.addUserSudoku(us1);
        usd.addUserSudoku(us2);
        usd.addUserSudoku(us3);

        ArrayList<UserSudoku> sudokus = usd.getUsersSudokus(1);

        Assert.assertEquals(3, sudokus.size());
    }
}
