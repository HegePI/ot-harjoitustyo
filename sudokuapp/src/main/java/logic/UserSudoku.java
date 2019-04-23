package logic;

public class UserSudoku {

    private boolean completed;
    private String difficulty;
    private int[][] sudoku;
    private int[][] originalSudoku;

    public UserSudoku(int[][] sudoku, int[][] originalSudoku) {
        this.completed = false;
        this.sudoku = sudoku;
        this.originalSudoku = originalSudoku;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    public int[][] getOriginalSudoku() {
        return originalSudoku;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public void setSudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public void setOriginalSudoku(int[][] originalSudoku) {
        this.originalSudoku = originalSudoku;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String sudokuToString() {
        String sudokuString = "";
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                sudokuString += this.sudoku[y][x] + "";
            }
        }
        return sudokuString;
    }

    public String originalSudokuToString() {
        String sudokuString = "";
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                sudokuString += this.originalSudoku[y][x] + "";
            }
        }
        return sudokuString;
    }
}
