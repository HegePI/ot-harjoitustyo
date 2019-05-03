package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import logic.User;

/**
 *
 * @author hepulli
 */
public class UserDao {

    private final Database database;

    /**
     * Konstruktori, jossa luodaan UserDao -olion. Saa parametrina jo luodun
     * Database -olion, jolla luotu oli saa yhteyden tietokantaan.
     *
     * @param db Database -olio, jolla UserDao -olion saa yhteyden tietokantaan.
     */
    public UserDao(Database db) {
        this.database = db;
    }

    /**
     * Lisää tietokantaan parametrina saadun User -olion tiedot.
     *
     * @param newUser Tietokantaan lisättävä käyttäjä.
     * @return true, jos lisääminen onnistui, muuten false.
     * @throws SQLException
     */
    public boolean addUser(User newUser) throws SQLException {
        boolean succes;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("INSERT INTO User (name, pswd) VALUES (?,?)");
            stmnt.setString(1, newUser.getUserName());
            stmnt.setString(2, newUser.getPswd());

            stmnt.execute();

            stmnt.close();
            con.close();

            succes = true;
        } catch (Exception e) {
            System.out.println("UserDao, addUser(): " + e.getMessage());
            succes = false;
        }

        return succes;
    }

    /**
     * Palauttaa kaikki käyttäjät tietokannasta.
     *
     * @return Lista käyttäjistä, jotka ovat tietokannassa.
     */
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("SELECT * FROM User");

            ResultSet rs = stmnt.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("pswd"));
                user.setId(rs.getInt("id"));
                users.add(user);
            }

            stmnt.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("UserDao, getAll(): " + e.getMessage());
        }

        return users;
    }

    /**
     * Palauttaa User -olion, jonka nimi ja salasana vastaavat tietokannassa
     * olevaa käyttäjää.
     *
     * @param name haetun käyttäjän nimi
     * @param pswd haetun käyttäjän salasana
     * @return tietojen perusteella löydetty User -olio
     * @throws SQLException
     */
    public User getByNameAndPswd(String name, String pswd) throws SQLException {
        User user = null;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("SELECT * FROM User WHERE name = ? and pswd = ?");
            stmnt.setString(1, name);
            stmnt.setString(2, pswd);

            ResultSet rs = stmnt.executeQuery();

            user = new User(rs.getString("name"), rs.getString("pswd"));
            user.setId(rs.getInt("id"));

            stmnt.close();
            rs.close();
            con.close();

        } catch (Exception e) {
            System.out.println("UserDao, getByNameAndPswd(): " + e.getMessage());
        }

        return user;
    }

    /**
     * Hakee tietokannasta User -olion tiedot, jonka id vastaa parametrina
     * saatua id:tä.
     *
     * @param id haettavan User -olion id
     * @return id:tä vastaava User -olio
     * @throws SQLException
     */
    public User getById(int id) throws SQLException {
        User user = null;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("SELECT * FROM User WHERE id = ?");
            stmnt.setInt(1, id);

            ResultSet rs = stmnt.executeQuery();

            user = new User(rs.getString("name"), rs.getString("pswd"));
            user.setId(rs.getInt("id"));

            rs.close();
            stmnt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("UserDao, getById(): " + e.getMessage());
        }
        return user;
    }

    /**
     * Poistaa kaikkien User -olioiden tideot tietokannasta.
     *
     * @return true, jos poistaminen onnistui, muuten false
     */
    public boolean deleteAll() {
        boolean succes;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("DELETE FROM User");
            stmnt.execute();

            stmnt.close();
            con.close();

            succes = true;
        } catch (Exception e) {
            succes = false;
            System.out.println("UserDao, deleteAll(): " + e.getMessage());
        }
        return succes;
    }

    /**
     * Poistaa tietokannasta id:tä vastaavan User -olion tiedot.
     *
     * @param id Poistettavan User -oliota vastaava id.
     * @return true, jos poistami
     * @throws SQLException
     */
    public boolean deleteById(int id) throws SQLException {
        boolean succes;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("DELETE FROM User WHERE id = ?");
            stmnt.setInt(1, id);

            stmnt.execute();

            stmnt.close();
            con.close();
            succes = true;

        } catch (Exception e) {
            System.out.println("UserDao, deleteById(): " + e.getMessage());
            succes = false;
        }

        return succes;
    }

}
