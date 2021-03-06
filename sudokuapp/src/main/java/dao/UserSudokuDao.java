package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.UserSudoku;

/**
 *
 * @author hepulli
 */
public class UserSudokuDao {

    private final Database database;

    /**
     * Konstruktori, joka luo UserSudokuDao -olion. konstruktori saa parametrina
     * Database -olion, jonka avulla se saa yhteyden tietokantaan.
     *
     * @param database Database -olion, jonka avulla UserSudokuDao -olion saa
     * yhteyden tietokantaan.
     */
    public UserSudokuDao(Database database) {
        this.database = database;
    }

    /**
     * Lisää parametrina saadun UserSudoku -olion tiedot tietokantaan.
     *
     * @param us Lisättävä UserSudoku -olio.
     * @return palauttaa true, jos lisäys onnistui, muuten false.
     * @throws SQLException
     */
    public boolean addUserSudoku(UserSudoku us) throws SQLException {
        boolean succes = true;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement(""
                    + "INSERT INTO UserSudoku VALUES (?,?,?,?,?,?)");
            stmnt.setInt(1, us.getSudokuId());
            stmnt.setString(2, us.getDifficulty());
            stmnt.setBoolean(3, us.isCompleted());
            stmnt.setString(4, us.sudokuToString());
            stmnt.setString(5, us.originalSudokuToString());
            stmnt.setInt(6, us.getUserId());

            stmnt.execute();

            stmnt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("UserSudokuDao, add() metodi: " + e.getMessage());
            succes = false;
        }
        return succes;
    }

    /**
     * Hakee tietokannasta UserSudoku -olion tiedot, jonka sudokuId ja UserId
     * vastaavat parametrina saatuja id:jä.
     *
     * @param sudokuId sudokun yksilöivä id
     * @param userId käyttäjän yksilöivä id
     * @return tietokannasta löydetty UserSudoku -olio
     * @throws SQLException
     */
    public UserSudoku getByIds(int sudokuId, int userId) throws SQLException {
        UserSudoku us = null;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("SELECT * FROM UserSudoku WHERE id = ? AND user_id = ?");
            stmnt.setInt(1, sudokuId);
            stmnt.setInt(2, userId);
            ResultSet rs = stmnt.executeQuery();

            us = contructUserSudoku(rs);

        } catch (Exception e) {
            System.out.println("UserSudokuDao, getByIds(): " + e.getMessage());
        }
        return us;
    }

    /**
     * Tallentaa parametrina saaneensa UserSudoku -olion tiedot tietokantaan.
     *
     * @param us Tallennettava UserSudoku
     * @return palauttaa true, jos tallennus onnistui, muuten false
     */
    public boolean saveUserSudoku(UserSudoku us) {
        boolean succes = true;
        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("UPDATE UserSudoku SET sudoku = ?, completed = ? WHERE id = ? AND user_id = ?");
            stmnt.setString(1, us.sudokuToString());
            stmnt.setBoolean(2, us.isCompleted());
            stmnt.setInt(3, us.getSudokuId());
            stmnt.setInt(4, us.getUserId());

            stmnt.execute();

        } catch (Exception e) {
            System.out.println("UserSudokuDao, save(): " + e.getMessage());
            succes = false;
        }

        return succes;

    }

    /**
     * Poistaa kaikki tietokannasta löytyvät UserSudoku -oliot.
     *
     * @return palauttaa true, jos poistaminen onnistui, muuten false
     */
    public boolean deleteAll() {
        boolean succes = true;
        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("DELETE FROM UserSudoku");

            stmnt.execute();
            stmnt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("UserSudokuDao, deleteAll(): " + e.getMessage());
            succes = false;
        }
        return succes;
    }

    /**
     * Palauttaa kaikki UserSudoku -oliot, joiden UserId vastaa parametrina
     * saatua id:tä.
     *
     * @param userId käyttäjän yksilöivä id
     * @return lista UserSudoku -olioita, jotka liittyvät parametrina saatuun
     * id:hen.
     */
    public ArrayList<UserSudoku> getUsersSudokus(int userId) {
        ArrayList<UserSudoku> usersSudokus = new ArrayList<>();
        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("SELECT * FROM UserSudoku WHERE user_id = ?");
            stmnt.setInt(1, userId);

            ResultSet rs = stmnt.executeQuery();
            while (rs.next()) {
                usersSudokus.add(contructUserSudoku(rs));
            }
            rs.close();
            stmnt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("UserSudokuDao, getUsersSudokus(): " + e.getMessage());
        }
        return usersSudokus;
    }

    private UserSudoku contructUserSudoku(ResultSet rs) throws SQLException {
        UserSudoku us;
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

        us = new UserSudoku(s, os, rs.getInt("id"), rs.getInt("user_id"), rs.getString("difficulty"));
        us.setCompleted(rs.getBoolean("completed"));
        us.toString();
        return us;
    }

}
