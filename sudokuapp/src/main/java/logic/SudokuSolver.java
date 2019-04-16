package logic;

public class SudokuSolver {

    private static int lastFixedRow = 0;
    private static int lastValue = 0;
    boolean[][] originals = new boolean[9][9];

    public int[][] solve(int[][] sudoku) {
        initialize(sudoku);
        for (int value = 1; value <= 9; value++) {
            for (int row = 0; row < 9; row++) {
                int column = 0;
                boolean valueFound = false;
                for (column = 0; column < 9; column++) {
                    if (sudoku[row][column] == value) {
                        valueFound = true;
                        break;
                    }
                }
                if (valueFound) {
                    if (!original(sudoku, row, column)) {
                        fixRight(sudoku, row, value);
                        row = lastFixedRow;
                        value = lastValue;
                        row--;
                    }
                } else {
                    if (!setValue(sudoku, row, value)) {
                        if (row == 0) {
                            fixRight(sudoku, 8, value - 1);
                        } else {
                            fixRight(sudoku, row - 1, value);
                        }
                        row = lastFixedRow;
                        value = lastValue;
                    }
                }
            }
        }

        return sudoku;
    }

    private void initialize(int[][] sudoku) {
        boolean[][] booleanTable = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] != 0) {
                    booleanTable[i][j] = true;
                }
            }
        }
        originals = booleanTable;
    }

    private boolean original(int[][] sudoku, int row, int column) {
        return originals[row][column];
    }

    private void fixRight(int[][] sudoku, int row, int value) {
        int column = 0;
        boolean valueFound = false;
        for (column = 0; column < 9; column++) {
            if (sudoku[row][column] == value && originals[row][column] == false) {
                valueFound = true;
                sudoku[row][column] = 0;
                break;
            }
        }
        if (valueFound) {
            for (int i = column + 1; i < 9; i++) {
                if (slotFree(sudoku, row, i, value)) {
                    sudoku[row][i] = value;
                    lastFixedRow = row;
                    lastValue = value;
                    return;
                }
            }
            if (row == 0) {
                fixRight(sudoku, 8, value - 1);
            } else {
                fixRight(sudoku, row - 1, value);
            }
        } else {
            if (row == 0) {
                fixRight(sudoku, 8, value - 1);
            } else {
                fixRight(sudoku, row - 1, value);
            }
        }
    }

    private boolean slotFree(int[][] sudoku, int row, int column, int value) {
        return sudoku[row][column] == 0 && columnFree(sudoku, row, column, value) && bigSlotFree(sudoku, row, column, value);
    }

    private boolean columnFree(int[][] sudoku, int row, int column, int value) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][column] == value) {
                return false;
            }
        }
        return true;
    }

    private boolean bigSlotFree(int[][] sudoku, int row, int column, int value) {
        if (row < 3) {
            if (column < 3) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (sudoku[i][j] == value) {
                            return false;
                        }
                    }
                }
            } else if (column < 6) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 3; j < 6; j++) {
                        if (sudoku[i][j] == value) {
                            return false;
                        }
                    }
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    for (int j = 6; j < 9; j++) {
                        if (sudoku[i][j] == value) {
                            return false;
                        }
                    }
                }
            }
        } else if (row < 6) {
            if (column < 3) {
                for (int i = 3; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (sudoku[i][j] == value) {
                            return false;
                        }
                    }
                }
            } else if (column < 6) {
                for (int i = 3; i < 6; i++) {
                    for (int j = 3; j < 6; j++) {
                        if (sudoku[i][j] == value) {
                            return false;
                        }
                    }
                }
            } else {
                for (int i = 3; i < 6; i++) {
                    for (int j = 6; j < 9; j++) {
                        if (sudoku[i][j] == value) {
                            return false;
                        }
                    }
                }
            }
        } else {
            if (column < 3) {
                for (int i = 6; i < 9; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (sudoku[i][j] == value) {
                            return false;
                        }
                    }
                }
            } else if (column < 6) {
                for (int i = 6; i < 9; i++) {
                    for (int j = 3; j < 6; j++) {
                        if (sudoku[i][j] == value) {
                            return false;
                        }
                    }
                }
            } else {
                for (int i = 6; i < 9; i++) {
                    for (int j = 6; j < 9; j++) {
                        if (sudoku[i][j] == value) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean setValue(int[][] sudoku, int row, int value) {
        for (int column = 0; column < 9; column++) {
            if (slotFree(sudoku, row, column, value)) {
                sudoku[row][column] = value;
                return true;
            }
        }
        return false;
    }

}
