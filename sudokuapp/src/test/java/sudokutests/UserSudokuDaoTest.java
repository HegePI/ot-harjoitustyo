package sudokutests;


import dao.Database;
import dao.UserSudokuDao;
import java.sql.SQLException;
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

    public UserSudokuDaoTest() throws ClassNotFoundException, SQLException {
        this.db = new Database();
        this.usd = new UserSudokuDao(db);

    }

    @Before
    public void setUp() {
        usd.deleteAll();
    }

    @Test
    public void canAddUserSudoku() throws SQLException {

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

        boolean succes = usd.add(us);

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

        boolean succes = usd.add(us);

        UserSudoku us2 = usd.getByIds(1, 1);

        boolean succes2 = false;
        if (us2.getUserId() == us.getUserId() && us2.getSudokuId() == us.getSudokuId()) {
            succes2 = true;
        }
        Assert.assertEquals(true, succes2);
    }

    @Test
    public void save() throws SQLException {
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

        boolean succes = usd.add(us);

        us.setCell(0, 0, 1);

        usd.save(us);

        UserSudoku us2 = usd.getByIds(1, 1);

        boolean succes2 = false;

        if (us2.getSudoku()[0][0] == 1) {
            succes2 = true;
        }
        Assert.assertEquals(true, succes2);
    }
}
