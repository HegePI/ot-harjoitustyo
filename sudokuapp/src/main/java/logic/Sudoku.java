package logic;

/**
 *
 * @author hepulli
 */
public class Sudoku {

    private int id;
    private String difficulty;
    private int[][] sudoku;
    private final SudokuSolver sudokuSolver;

    /**
     * Sudoku -olion luova konstruktori. Parametrina annetaan sudoku 2d
     * -taulukkona ja vaikeusaste.
     *
     * @param difficulty Sudokun vaikeusaste
     * @param sudoku sudoku 2d integer taulukkona
     */
    public Sudoku(String difficulty, int[][] sudoku) {
        this.difficulty = difficulty;
        this.sudoku = sudoku;
        this.sudokuSolver = new SudokuSolver();
    }

    public int getSudokuId() {
        return id;
    }

    public void setSudokuId(int id) {
        this.id = id;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    @Override
    public String toString() {
        String toString = "";
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

    /**
     * Muodostaa sudokun kaksiulotteiseta esittämismallista String esitysmallin,
     * joka voidaan tallentaa tietokantaan
     *
     * @return sudokun String esitys
     */
    public String sudokuToString() {
        String sudoku = "";
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                sudoku += this.sudoku[y][x] + "";
            }
        }
        return sudoku;
    }

    /**
     * kertoo, onko kaksi sudokua samat. true: samat false: ei samat
     *
     * @param s - vertailtava Sudoku -olio
     *
     * @see logic.Sudoku
     *
     * @return palauttaa true, jos samat sudokut, muuten false
     *
     */
    public boolean equals(Sudoku s) {
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

}
