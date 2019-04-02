package sudokuApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sudokuDao {

    private final Database database;

    public sudokuDao(Database database) {
        this.database = database;
    }

    public boolean addSudoku(sudoku s) throws SQLException {
        boolean succes;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement(
                    "INSERT INTO Sudoku (difficulty, completed, sudoku) VALUES (?,?,?)");
            stmnt.setString(1, s.getDifficulty());
            stmnt.setBoolean(2, s.isCompleted());
            stmnt.setString(3, s.sudokuToString());

            stmnt.execute();

            stmnt.close();
            con.close();

            succes = true;

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            succes = false;
        }
        return succes;
    }

    public sudoku getById(int id) {
        sudoku s = null;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("SELECT difficulty, completed, sudoku FROM Sudoku WHERE id = ?");
            stmnt.setInt(1, id);

            ResultSet rs = stmnt.executeQuery();

            String sudokuString = rs.getString("sudoku");
            int[][] sudokuArray = new int[9][9];
            int index = 0;
            
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    sudokuArray[y][x] = Integer.parseInt(Character.toString(sudokuString.charAt(index)));
                    index++;
                }
            }

            s = new sudoku(rs.getBoolean("completed"), rs.getString("difficulty"), sudokuArray);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return s;
    }

    public boolean deleteById(int id) throws SQLException {
        boolean succes;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("DELETE FROM Sudoku WHERE id = ?");
            stmnt.setInt(1, id);

            stmnt.execute();

            stmnt.close();
            con.close();

            succes = true;

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            succes = false;
        }
        return succes;
    }

    public boolean deleteAll() throws SQLException {
        boolean succes;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("DELETE FROM Sudoku");

            stmnt.execute();

            stmnt.close();
            con.close();

            succes = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            succes = false;
        }
        return succes;
    }

}
