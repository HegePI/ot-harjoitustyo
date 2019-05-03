package logic;

/**
 *
 * @author hepulli
 */
public class UserSudoku {

    private int sudokuId;
    private int userId;
    private boolean completed;
    private String difficulty;
    private int[][] sudoku;
    private int[][] originalSudoku;
    private final SudokuSolver solver;

    /**
     * UserSudoku -olion luova konstruktori. Parametrina annetaan sudokun
     * nykyinen tilanne, alkuperäinen sudoku, sudokun yksilöivä id, käyttäjän
     * yksilöivä id ja sudokun vaikeusaste.
     *
     * @param sudoku sudokun tilanne 2d integer taulukkona
     * @param originalSudoku alkuperäinen sudoku 2d integer taulukkona
     * @param sudokuId sudokun yksilöivä id
     * @param userId käyttäjän yksilöivä id
     * @param difficulty sudokun vaikeusaste
     */
    public UserSudoku(int[][] sudoku, int[][] originalSudoku, int sudokuId, int userId, String difficulty) {
        this.completed = false;
        this.sudoku = sudoku;
        this.originalSudoku = originalSudoku;
        this.sudokuId = sudokuId;
        this.userId = userId;
        this.difficulty = difficulty;
        this.solver = new SudokuSolver();
    }

    public int getSudokuId() {
        return sudokuId;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.sudokuId = id;
    }

    /**
     * Asettaa parametrina saadun arvon row ja col parametrien osoittamaan
     * sudokun ruutuun.
     *
     * @param row rivinumero
     * @param col sarakenumero
     * @param value ruutuun laitettava arvo
     */
    public void setCell(int row, int col, int value) {
        this.sudoku[row][col] = value;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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

    /**
     * Muodostaa UserSudoku -olion sudoku taulukosta String esityksen.
     *
     * @return palauttaa sudokun String esityksen
     */
    public String sudokuToString() {
        String sudokuString = "";
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                sudokuString += this.sudoku[y][x] + "";
            }
        }
        return sudokuString;
    }

    /**
     * Muodostaa alkuperäisestä sudokusta String esityksen
     *
     * @return palauttaa alkuperäisen sudokun String esityksen
     */
    public String originalSudokuToString() {
        String sudokuString = "";
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                sudokuString += this.originalSudoku[y][x] + "";
            }
        }
        return sudokuString;
    }

    /**
     * Ratkaisee sen sudokun, joka kutsuu tätä metodia. Metodi ei palauta
     * kaikkia mahdollisia ratkaisuja, vaan yhden ratkaisun.
     *
     *
     */
    public void solve() {
        int[][] originalCopy = sudokuCopy(this.originalSudoku);
        int[][] solvedSudoku = solver.solve(originalCopy);
        setSudoku(solvedSudoku);
        setCompleted();
    }

    public boolean equals(UserSudoku s) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (this.sudoku[y][x] != s.getSudoku()[y][x]) {
                    return false;
                }
            }
        }
        if (this.difficulty.equals(s.getDifficulty())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String toString = "";
        toString += "difficulty: " + difficulty;
        if (completed) {
            toString += ", ratkaistu" + "\n\n";
        } else {
            toString += ", keskeneräinen" + "\n\n";
        }

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

    private int[][] sudokuCopy(int[][] originalSudoku) {
        int[][] copy = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                copy[row][col] = this.originalSudoku[row][col];
            }
        }
        return copy;
    }

}
