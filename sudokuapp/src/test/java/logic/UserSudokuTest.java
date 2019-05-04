/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author heikki
 */
public class UserSudokuTest {

    public UserSudokuTest() {
    }

    @Test
    public void sudokuToString() {
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
        UserSudoku s = new UserSudoku(sudoku, sudoku, 1, 1, "easy");
        String sudokuString = s.sudokuToString();

        String comparable = "000507000002406300090010020270000068003000100140000093060040050009205600000903000";
        Assert.assertEquals(comparable, sudokuString);
    }

    @Test
    public void solve() {
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

        us2.solve();

        boolean same = false;

        if (us1.equals(us2)) {
            same = true;
        }
        assertEquals(true, same);
    }

}
