package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import logic.Sudoku;
import org.sqlite.SQLiteConfig;

public class Database {

    SQLiteConfig config;

    public Database() throws ClassNotFoundException, SQLException {
        this.config = new SQLiteConfig();
        File file = new File("sudoku.db");
        if (!file.exists()) {
            initdb(file);
        }
    }

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
        String userTable = "CREATE TABLE IF NOT EXISTS User ("
                + "id Integer PRIMARY KEY,"
                + " name VARCHAR(15),"
                + " pswd VARCHAR(15));";

        String userSudokuTable = "CREATE TABLE IF NOT EXISTS UserSudoku ("
                + "id Integer PRIMARY KEY,"
                + " difficulty VARCHAR(10),"
                + " completed Integer,"
                + " sudoku VARCHAR(90),"
                + " originalSudoku VARCHAR(90),"
                + " user_id Integer, "
                + " FOREIGN KEY (user_id)"
                + " REFERENCES User(id)"
                + " ON DELETE CASCADE);";

        String sudokuTable = "CREATE TABLE IF NOT EXISTS Sudoku ("
                + "id Integer PRIMARY KEY,"
                + " difficulty VARCHAR(10),"
                + " sudoku VARCHAR(90));";

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

    private void setDefaultSudokus() throws SQLException {
        Sudokudao sd = new Sudokudao(this);
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
        Sudoku s1 = new Sudoku("easy", sa);
        Sudoku s2 = new Sudoku("Maailman vaikein sudoku", sb);

        sd.addSudoku(s1);
        sd.addSudoku(s2);
    }
}
