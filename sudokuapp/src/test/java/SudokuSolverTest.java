
import logic.Sudoku;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heikki
 */
public class SudokuSolverTest {

    public SudokuSolverTest() {
    }

    @Test
    public void works() {

        int[][] sudoku = {
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
        Sudoku s1 = new Sudoku(true, "a", sudoku);
        Sudoku s2 = new Sudoku(true, "a", solved);
        s1.solve();

        boolean same = false;
        if (s1.equals(s2)) {
            same = true;
        }

        assertEquals(same, true);

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
