/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heikki
 */
public class DatabaseTest {

    private Database db;

    public DatabaseTest() {
    }

    @Before
    public void setUp() {
        File file = new File("sudoku.db");
        if (file.exists()) {
            file.delete();
        }
    }

    @After
    public void deleteDB() {
        File file = new File("sudoku.db");
        if (file.exists()) {
            file.delete();
        }
    }

//    @Test
//    public void initDB() throws ClassNotFoundException, SQLException {
//        boolean succes = true;
//        File file = new File("sudoku.db");
//
//        this.db = new Database();
//
//        if (!file.exists()) {
//            succes = false;
//        }
//        assertEquals(true, succes);
//
//    }
}
