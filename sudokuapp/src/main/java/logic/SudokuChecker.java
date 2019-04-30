package logic;

import java.util.*;

public class SudokuChecker {

    private ArrayList<Integer>[] values;

    public boolean checkSudoku(Sudoku s) {
        if (!checkRows(s)) {
            return false;
        }
        if (!checkCols(s)) {
            return false;
        }
        if (checkBoxes(s)) {
            return false;
        }
        return true;
    }

    private boolean checkRows(Sudoku s) {
        this.values = new ArrayList[9];
        int[][] sudoku = s.getSudoku();

        for (int row = 0; row < 9; row++) {
            values[row] = new ArrayList<>();
            for (int col = 0; col < 9; col++) {
                if (values[row].contains(sudoku[row][col])) {
                    return false;
                } else {
                    values[row].add(sudoku[row][col]);
                }
            }
        }
        return true;
    }

    private boolean checkCols(Sudoku s) {
        this.values = new ArrayList[9];
        int[][] sudoku = s.getSudoku();

        for (int col = 0; col < 9; col++) {
            values[col] = new ArrayList<>();
            for (int row = 0; row < 9; row++) {
                if (values[col].contains(sudoku[col][row])) {
                    return false;
                } else {
                    values[col].add(sudoku[col][row]);
                }
            }
        }

        return true;
    }

    private boolean checkBoxes(Sudoku s) {
        this.values = new ArrayList[9];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                

            }

        }
        return true;
    }
}
