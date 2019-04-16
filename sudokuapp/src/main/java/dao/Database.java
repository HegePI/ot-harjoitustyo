package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public Database() throws ClassNotFoundException {

    }

    public Connection getConnection() throws SQLException {
//        File file = new File("src/main/resources/db", "sudoku.db");
        File file = new File("sudoku.db");
        if (!file.exists()) {
            initdb();
        }
        String path = ("jdbc:sqlite:" + file.getAbsolutePath());
        return DriverManager.getConnection(path);
    }
    //Creates .db file and required tables, if directory doesn't contain it.
    private void initdb() {
        System.out.println("Alustetaan tietokanta");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
