package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteConfig;

public class Database {

    SQLiteConfig config;

    public Database() throws ClassNotFoundException {
        this.config = new SQLiteConfig();
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
                + " FOREIGN KEY (id.)"
                + " REFERENCES User(id)"
                + " ON DELETE CASCADE);";

        String sudokuTable = "CREATE TABLE IF NOT EXISTS Sudoku ("
                + "id Integer PRIMARY KEY,"
                + " difficulty VARCHAR(10),"
                + " completed Integer,"
                + " sudoku VARCHAR(90));";

        try (Connection con = DriverManager.getConnection(path);
                Statement stmnt = con.createStatement()) {
            stmnt.execute(userTable);
            stmnt.execute(userSudokuTable);
            stmnt.execute(sudokuTable);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());

        }
    }
}
