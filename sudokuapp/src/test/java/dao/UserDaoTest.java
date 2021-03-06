package dao;

import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import logic.User;
import java.util.*;

public class UserDaoTest {

    Database db;
    UserDao users;

    public UserDaoTest() throws ClassNotFoundException, SQLException {
        db = new Database();
        users = new UserDao(db);
    }

    @Before
    public void setUp() {
        users.deleteAll();
    }

    @Test
    public void addUser() throws SQLException {
        User user1 = new User("helppo", "heikki");
        users.addUser(user1);
        User user2 = users.getById(1);
        boolean sameUsers = false;

        if (user1.equals(user2)) {
            sameUsers = true;
        }

        Assert.assertEquals(sameUsers, true);
    }

    @Test
    public void getAll() throws SQLException {

        users.addUser(new User("a", "b"));
        users.addUser(new User("c", "d"));
        users.addUser(new User("e", "f"));
        users.addUser(new User("g", "h"));

        ArrayList<User> userList = users.getAll();
        Assert.assertEquals(userList.size(), 4);
    }

    @Test
    public void getByNameAndPswd() throws SQLException {

        User user1 = new User("t", "t");
        users.addUser(user1);

        User user2 = users.getByNameAndPswd("t", "t");

        boolean sameUsers = false;

        if (user1.equals(user2)) {
            sameUsers = true;
        }

        Assert.assertEquals(sameUsers, true);
    }

    @Test
    public void getById() throws SQLException {

        User user1 = new User("b", "b");

        users.addUser(new User("a", "a")); //1
        users.addUser(user1); //2
        users.addUser(new User("c", "c")); //3

        User user2 = users.getById(2);

        boolean sameUsers = false;

        if (user1.equals(user2)) {
            sameUsers = true;
        }

        Assert.assertEquals(sameUsers, true);
    }

    @Test
    public void deleteAll() throws SQLException {

        users.addUser(new User("a", "a"));
        users.addUser(new User("b", "b"));
        users.addUser(new User("c", "c"));
        users.addUser(new User("d", "d"));
        users.addUser(new User("e", "e"));

        users.deleteAll();

        ArrayList<User> userList = users.getAll();

        Assert.assertEquals(userList.size(), 0);
    }

    @Test
    public void deleteById() throws SQLException {

        User user1 = new User("a", "a");
        User user2 = new User("b", "b");
        User user3 = new User("c", "c");

        users.addUser(user1); //id: 1
        users.addUser(user2); //id: 2
        users.addUser(user3); //id: 3

        users.deleteById(2);

        ArrayList<User> userList2 = users.getAll();

        boolean deleted = false;

        if (!userList2.contains(user2)) {
            deleted = true;
        }

        Assert.assertEquals(deleted, true);
    }

}
