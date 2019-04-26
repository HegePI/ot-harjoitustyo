package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import logic.User;

public class Userdao {

    private final Database database;

    public Userdao(Database db) {
        this.database = db;
    }

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
            System.out.println("Exception: " + e);
            succes = false;
        }

        return succes;
    }

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
            System.out.println("Exception: " + e);
        }

        return users;
    }

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
            System.out.println("Exception: " + e);
        }

        return user;
    }

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
