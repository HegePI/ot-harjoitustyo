package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logic.UserSudoku;

public class UserSudokuDao {

    private final Database database;

    public UserSudokuDao(Database database) {
        this.database = database;
    }

    public boolean add(UserSudoku us) throws SQLException {
        boolean succes = false;

        System.out.println("Lisätään " + us.toString());

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement(""
                    + "INSERT INTO UserSudoku VALUES (?,?,?,?,?,?)");
            stmnt.setInt(1, us.getSudokuId());
            stmnt.setString(2, us.getDifficulty());
            stmnt.setBoolean(3, us.isCompleted());
            stmnt.setString(4, us.sudokuToString());
            stmnt.setString(5, us.originalSudokuToString());
            stmnt.setInt(6, us.getUserId());

            succes = stmnt.execute();

            stmnt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("UserSudokuDao, add() metodi: " + e.getMessage());
        }
        return succes;
    }

    public UserSudoku getByIds(int sudokuId, int userId) throws SQLException {
        UserSudoku us = null;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("SELECT * FROM UserSudoku WHERE id = ? AND user_id = ?");
            stmnt.setInt(1, sudokuId);
            stmnt.setInt(2, userId);
            ResultSet rs = stmnt.executeQuery();

            String sudokuString = rs.getString("sudoku");
            String originalSudokuString = rs.getString("originalSudoku");
            int[][] s = new int[9][9];
            int[][] os = new int[9][9];
            int index = 0;

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    s[i][j] = Integer.parseInt(Character.toString(sudokuString.charAt(index)));
                    os[i][j] = Integer.parseInt(Character.toString(originalSudokuString.charAt(index)));
                    index++;
                }
            }

            us = new UserSudoku(s, os);
            us.setId(rs.getInt("id"));
            us.setCompleted(rs.getBoolean("completed"));
            us.setDifficulty(rs.getString("difficulty"));
            us.setUserId(rs.getInt("user_id"));

        } catch (Exception e) {
            System.out.println("UserSudokuDao, getById(): " + e.getMessage());

        }
        return us;
    }

}
