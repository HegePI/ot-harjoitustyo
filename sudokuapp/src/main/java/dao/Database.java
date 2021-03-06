package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import logic.Sudoku;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author hepulli
 */
public class Database {

    SQLiteConfig config;

    /**
     * Database olion konstruktori, jossa tarkistetaan, onko ohjelmaa
     * ajettavassa tiedostossa tietokantaa. jos ei ole, niin se luodaan sinne.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Database() throws ClassNotFoundException, SQLException {
        this.config = new SQLiteConfig();
        File file = new File("sudoku.db");
        if (!file.exists()) {
            initdb(file);
        }
    }

    /**
     * Luo yhteys olion tietokantaan Drivemanager -olion avulla ja palauttaa
     * sen.
     *
     * @return @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        config.enforceForeignKeys(true);
        File file = new File("sudoku.db");
        if (!file.exists()) {
            initdb(file);
        }
        String path = ("jdbc:sqlite:" + file.getAbsolutePath());
        return DriverManager.getConnection(path, config.toProperties());
    }

    private void initdb(File f) throws SQLException {
        System.out.println("Alustetaan tietokanta");
        String path = ("jdbc:sqlite:" + f.getAbsolutePath());
        String userTable = createUserTable();
        String userSudokuTable = createUserSudokuTable();
        String sudokuTable = createSudokuTable();

        try (Connection con = DriverManager.getConnection(path);
                Statement stmnt = con.createStatement()) {
            stmnt.execute(userTable);
            stmnt.execute(userSudokuTable);
            stmnt.execute(sudokuTable);
        } catch (Exception e) {
            System.out.println("Database, initdb()" + e.getMessage());

        }
        setDefaultSudokus();
    }

    private String createUserTable() {
        String userTable = "CREATE TABLE IF NOT EXISTS User ("
                + "id Integer PRIMARY KEY,"
                + " name VARCHAR(15),"
                + " pswd VARCHAR(15));";

        return userTable;
    }

    private String createUserSudokuTable() {
        String userSudokuTable = "CREATE TABLE IF NOT EXISTS UserSudoku ("
                + "id Integer,"
                + " difficulty VARCHAR(10),"
                + " completed Integer,"
                + " sudoku VARCHAR(90),"
                + " originalSudoku VARCHAR(90),"
                + " user_id Integer, "
                + " PRIMARY KEY(id, user_id),"
                + " FOREIGN KEY (user_id)"
                + " REFERENCES User(id)"
                + " ON DELETE CASCADE)";

        return userSudokuTable;
    }

    private String createSudokuTable() {
        String sudokuTable = "CREATE TABLE IF NOT EXISTS Sudoku ("
                + "id Integer PRIMARY KEY,"
                + " difficulty VARCHAR(10),"
                + " sudoku VARCHAR(90));";

        return sudokuTable;
    }

    private void setDefaultSudokus() throws SQLException {
        addEasySudoku();
        addMediumSudoku();
        addHardSudoku();
    }

    private void addEasySudoku() throws SQLException {
        SudokuDao sd = new SudokuDao(this);
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
        sd.addSudoku(s1);
    }

    private void addMediumSudoku() throws SQLException {
        SudokuDao sd = new SudokuDao(this);
        int[][] sa = {
            {0, 0, 0, 5, 4, 0, 0, 0, 8},
            {6, 0, 0, 0, 0, 2, 3, 0, 0},
            {0, 0, 7, 0, 0, 3, 0, 9, 0},
            {0, 3, 1, 0, 5, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 0, 0, 3, 0, 7, 1, 0},
            {0, 9, 0, 7, 0, 0, 2, 0, 0},
            {0, 0, 8, 6, 0, 0, 0, 0, 5},
            {1, 0, 0, 0, 2, 4, 0, 0, 0}
        };
        Sudoku s1 = new Sudoku("medium", sa);
        sd.addSudoku(s1);
    }

    private void addHardSudoku() throws SQLException {
        SudokuDao sd = new SudokuDao(this);
        int[][] sb = {
            {0, 0, 5, 3, 0, 0, 0, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 2, 0},
            {0, 7, 0, 0, 1, 0, 5, 0, 0},
            {4, 0, 0, 0, 0, 5, 3, 0, 0},
            {0, 1, 0, 0, 7, 0, 0, 0, 6},
            {0, 0, 3, 2, 0, 0, 0, 8, 0},
            {0, 6, 0, 5, 0, 0, 0, 0, 9},
            {0, 0, 4, 0, 0, 0, 0, 3, 0},
            {0, 0, 0, 0, 0, 9, 7, 0, 0}
        };

        Sudoku s2 = new Sudoku("Maailman vaikein sudoku", sb);
        sd.addSudoku(s2);
    }
}
