package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Sudoku;

public class Sudokudao {

    private final Database database;

    public Sudokudao(Database database) {
        this.database = database;
    }

    public boolean addSudoku(Sudoku s) throws SQLException {
        boolean succes;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement(
                    "INSERT INTO Sudoku (difficulty, sudoku) VALUES (?,?)");
            stmnt.setString(1, s.getDifficulty());
            stmnt.setString(2, s.sudokuToString());

            stmnt.execute();

            stmnt.close();
            con.close();

            succes = true;

        } catch (Exception e) {
            System.out.println("SudokuDao, addSudoku(): " + e.getMessage());
            succes = false;
        }
        return succes;
    }

    public Sudoku getById(int id) {
        Sudoku s = null;
        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("SELECT * FROM Sudoku WHERE id = ?");
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

            s = new Sudoku(rs.getString("difficulty"), sudokuArray);
            s.setId(rs.getInt("id"));

            rs.close();
            stmnt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("SudokuDao, getById(): " + e.getMessage());
        }
        return s;
    }

    public ArrayList<Sudoku> getAll() throws SQLException {
        ArrayList<Sudoku> sudokus = new ArrayList<>();
        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("SELECT * FROM Sudoku");
            ResultSet rs = stmnt.executeQuery();

            while (rs.next()) {

                String sudokuString = rs.getString("sudoku");
                int[][] sudokuArray = new int[9][9];
                int index = 0;
                for (int y = 0; y < 9; y++) {
                    for (int x = 0; x < 9; x++) {
                        sudokuArray[y][x] = Integer.parseInt(Character.toString(sudokuString.charAt(index)));
                        index++;
                    }
                }
                Sudoku s = new Sudoku(rs.getString("difficulty"), sudokuArray);
                s.setId(rs.getInt("id"));
                sudokus.add(s);
            }

            rs.close();
            stmnt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("SudokuDao, getAll(): " + e.getMessage());
        }
        return sudokus;
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
            System.out.println("SudokuDao, deleteById(): " + e.getMessage());
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
            System.out.println("SudokuDao, deleteAll(): " + e.getMessage());
            succes = false;
        }
        return succes;
    }

}
