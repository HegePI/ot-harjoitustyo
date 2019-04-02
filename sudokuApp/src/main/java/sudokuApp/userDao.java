package sudokuApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class userDao {

    private final Database database;

    public userDao(Database db) {
        this.database = db;
    }

    public boolean addUser(user newUser) throws SQLException {
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
            System.out.println("Exception: " + e);
            succes = false;
        }

        return succes;
    }

    public ArrayList<user> getAll() {
        ArrayList<user> users = new ArrayList<>();

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("SELECT * FROM User");

            ResultSet rs = stmnt.executeQuery();

            while (rs.next()) {
                user user = new user(rs.getString("name"), rs.getString("pswd"));
                users.add(user);
            }

            stmnt.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return users;
    }

    public user getByNameAndPswd(String name, String pswd) throws SQLException {
        user user = null;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("SELECT * FROM User WHERE name = ? and pswd = ?");
            stmnt.setString(1, name);
            stmnt.setString(2, pswd);

            ResultSet rs = stmnt.executeQuery();

            user = new user(rs.getString("name"), rs.getString("pswd"));

            stmnt.close();
            rs.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return user;
    }

    public user getById(int id) throws SQLException {
        user user = null;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("SELECT name, pswd FROM User WHERE id = ?");
            stmnt.setInt(1, id);

            ResultSet rs = stmnt.executeQuery();

            user = new user(rs.getString("name"), rs.getString("pswd"));

            rs.close();
            stmnt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return user;
    }

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
            System.out.println("Exception: " + e);
        }
        return succes;
    }

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
            System.out.println("Exception: " + e);
            succes = false;
        }

        return succes;
    }

}
