package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    public Database() throws ClassNotFoundException {

    }

    public Connection getConnection() throws SQLException {
//        File file = new File("src/main/resources/db", "sudoku.db");
        File file = new File("sudoku.db");
        if (!file.exists()) {
            initdb(file);
        }
        String path = ("jdbc:sqlite:" + file.getAbsolutePath());
        return DriverManager.getConnection(path);
    }

    //Creates .db file and required tables, if directory doesn't contain it.
    private void initdb(File f) throws SQLException {
        System.out.println("Alustetaan tietokanta");
        String path = ("jdbc:sqlite:" + f.getAbsolutePath());
        String userTable = "CREATE TABLE IF NOT EXISTS User ("
                + "id Integer PRIMARY KEY,"
                + "name VARCHAR(15),"
                + "pswd VARCHAR(15));";

        String sudokuTable = "CREATE TABLE IF NOT EXISTS Sudoku ("
                + "id Integer PRIMARY KEY,"
                + "difficulty VARCHAR(10),"
                + "completed Integer,"
                + "sudoku VARCHAR(90));";

        try (Connection con = DriverManager.getConnection(path);
                Statement stmnt = con.createStatement()) {
            stmnt.execute(userTable);
            stmnt.execute(sudokuTable);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());

        }
    }
}
