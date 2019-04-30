package logic;

import java.util.*;

public class SudokuChecker {

    private ArrayList<Integer>[] values;

    /**
     * Metodi, joka tarkastaa onko käyttäjän sudoku oikein vai ei. Tarkistaa
     * rivit, sarakkeet ja 3x3 ruudukot omissa metodeissaan.
     *
     * @param s tarkistettava 2d -integer taulukko
     * @return true, jos oikein, false, jos väärin
     */
    public boolean checkSudoku(int[][] s) {
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

    private boolean checkRows(int[][] s) {
        this.values = new ArrayList[9];

        for (int row = 0; row < 9; row++) {
            values[row] = new ArrayList<>();
            for (int col = 0; col < 9; col++) {
                if (s[row][col] == 0) {
                    return false;
                }
                if (values[row].contains(s[row][col])) {
                    return false;
                } else {
                    values[row].add(s[row][col]);
                }
            }
        }
        return true;
    }

    private boolean checkCols(int[][] s) {
        this.values = new ArrayList[9];

        for (int col = 0; col < 9; col++) {
            values[col] = new ArrayList<>();
            for (int row = 0; row < 9; row++) {
                if (s[col][row] == 0) {
                    return false;
                }
                if (values[col].contains(s[col][row])) {
                    return false;
                } else {
                    values[col].add(s[col][row]);
                }
            }
        }

        return true;
    }

    private boolean checkBoxes(int[][] s) {
        this.values = new ArrayList[9];
        int row = 3;
        int col = 3;
        int i = 0;
        while (row < 9) {
            while (col < 9) {
                values[i] = new ArrayList<>();
                for (int x = row - 3; x < row; x++) {
                    for (int y = col - 3; y < col; y++) {
                        if (s[row][col] == 0) {
                            return false;
                        }
                        if (values[i].contains(s[row][col])) {
                            return false;
                        } else {
                            values[i].add(s[row][col]);
                        }
                    }
                }
                i++;
                col += 3;
            }
            col = 3;
            row += 3;
        }

        return true;
    }
}
