/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heikki
 */
public class SudokuCheckerTest {

    private SudokuChecker sc;

    public SudokuCheckerTest() {
        this.sc = new SudokuChecker();
    }

    @Test
    public void checkSudoku() {
        int[][] sa = {
            {1, 4, 5, 3, 2, 7, 6, 9, 8},
            {8, 3, 9, 6, 5, 4, 1, 2, 7},
            {6, 7, 2, 9, 1, 8, 5, 4, 3},
            {4, 9, 6, 1, 8, 5, 3, 7, 2},
            {2, 1, 8, 4, 7, 3, 9, 5, 6},
            {7, 5, 3, 2, 9, 6, 4, 8, 1},
            {3, 6, 7, 5, 4, 2, 8, 1, 9},
            {9, 8, 4, 7, 6, 1, 2, 3, 5},
            {5, 2, 1, 8, 3, 9, 7, 6, 4}
        };

        boolean succes = sc.checkSudoku(sa);

        assertEquals(true, succes);

    }
}
