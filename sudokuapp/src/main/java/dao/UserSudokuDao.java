/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import logic.UserSudoku;

/**
 *
 * @author heikki
 */
public class UserSudokuDao {

    private final Database database;

    public UserSudokuDao(Database database) {
        this.database = database;
    }

    public boolean add(UserSudoku us) throws SQLException {
        boolean succes = false;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement(""
                    + "INSERT INTO UserSudoku VALUES (?,?,?,?,?)");
            stmnt.setString(1, us.getDifficulty());
            stmnt.setBoolean(2, us.isCompleted());
            stmnt.setString(3, us.sudokuToString());
            stmnt.setString(4, us.originalSudokuToString());
            stmnt.setInt(5, us.getUserId());

            succes = stmnt.execute();

            stmnt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return succes;
    }

}
