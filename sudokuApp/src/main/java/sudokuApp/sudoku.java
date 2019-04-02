package sudokuApp;

public class sudoku {

    private boolean completed;
    private String difficulty;
    private int[][] sudoku;

    public sudoku(boolean completed, String difficulty, int[][] sudoku) {
        this.completed = completed;
        this.difficulty = difficulty;
        this.sudoku = sudoku;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        String toString = "";
        toString += "completed: " + completed + "\n";
        toString += "difficulty: " + difficulty + "\n\n";

        String sudokuString = "";

        for (int y = 0; y < sudoku[0].length; y++) {
            for (int x = 0; x < sudoku.length; x++) {
                sudokuString += sudoku[y][x] + " ";
            }
            sudokuString += "\n";
        }
        toString += sudokuString;
        return toString;
    }

    public String sudokuToString() {
        String sudoku = "";
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                sudoku += this.sudoku[y][x] + "";
            }
        }
        return sudoku;
    }

}
