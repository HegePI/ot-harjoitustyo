
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import dao.Database;
import dao.Sudokudao;
import logic.Sudoku;

public class SudokuDaoTest {

    Database db;
    dao.Sudokudao sudokuDao;

    public SudokuDaoTest() throws ClassNotFoundException, SQLException {
        this.db = new Database();
        sudokuDao = new Sudokudao(db);
    }

    @Before
    public void setUp() throws SQLException {
        sudokuDao.deleteAll();

    }

    @Test
    public void CanAddSudoku() throws SQLException {
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
        boolean success = sudokuDao.addSudoku(s1);
        Assert.assertEquals(true, success);
    }

    @Test
    public void getSudokuById() throws SQLException {
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
        sudokuDao.addSudoku(s1);

        boolean success = false;

        Sudoku s2 = sudokuDao.getById(1);

        if (s1.equals(s2)) {
            success = true;
        }
        Assert.assertEquals(true, success);
    }

    @Test
    public void getAll() throws SQLException {
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
        Sudoku s2 = new Sudoku(false, "normal", sa);
        Sudoku s3 = new Sudoku(false, "hard", sa);

        sudokuDao.addSudoku(s1);
        sudokuDao.addSudoku(s2);
        sudokuDao.addSudoku(s3);

        ArrayList<Sudoku> sudokus = sudokuDao.getAll();

        Assert.assertEquals(3, sudokus.size());
    }

    @Test
    public void canDeleteById() throws SQLException {
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
        sudokuDao.addSudoku(s1);
        sudokuDao.deleteById(1);
        Sudoku s2 = sudokuDao.getById(1);

        Assert.assertEquals(null, s2);
    }

    @Test
    public void canDeleteAll() throws SQLException {
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
        Sudoku s2 = new Sudoku(false, "normal", sa);
        Sudoku s3 = new Sudoku(false, "hard", sa);

        sudokuDao.addSudoku(s1);
        sudokuDao.addSudoku(s2);
        sudokuDao.addSudoku(s3);

        sudokuDao.deleteAll();

        ArrayList<Sudoku> sudokus = sudokuDao.getAll();

        Assert.assertEquals(0, sudokus.size());
    }
}
