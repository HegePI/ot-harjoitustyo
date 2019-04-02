
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sudokuApp.Database;
import sudokuApp.user;
import sudokuApp.userDao;
import java.util.*;

public class userDaoTest {

    Database db;
    userDao users;

    public userDaoTest() throws ClassNotFoundException {
        db = new Database();
        users = new userDao(db);

    }

    @Before
    public void setUp() {
        users.deleteAll();
    }

    @Test
    public void canAddUserToDB() throws SQLException {
        user user1 = new user("helppo", "heikki");
        boolean succes = users.addUser(user1);
        user user2 = users.getById(1);
        boolean sameUsers = false;

        if (user1.equals(user2)) {
            sameUsers = true;
        }

        Assert.assertEquals(sameUsers, true);
    }

    @Test
    public void canGetAllUsers() throws SQLException {

        users.addUser(new user("a", "b"));
        users.addUser(new user("c", "d"));
        users.addUser(new user("e", "f"));
        users.addUser(new user("g", "h"));

        ArrayList<user> userList = users.getAll();

        Assert.assertEquals(userList.size(), 4);
    }

    @Test
    public void userByUsernameAndPswd() throws SQLException {

        user user1 = new user("t", "t");
        users.addUser(user1);

        user user2 = users.getByNameAndPswd("t", "t");

        boolean sameUsers = false;

        if (user1.equals(user2)) {
            sameUsers = true;
        }

        Assert.assertEquals(sameUsers, true);
    }

    @Test
    public void userById() throws SQLException {

        user user1 = new user("b", "b");

        users.addUser(new user("a", "a")); //1
        users.addUser(user1); //2
        users.addUser(new user("c", "c")); //3

        user user2 = users.getById(2);

        boolean sameUsers = false;

        if (user1.equals(user2)) {
            sameUsers = true;
        }

        Assert.assertEquals(sameUsers, true);
    }

    @Test
    public void canDeleteAll() throws SQLException {

        users.addUser(new user("a", "a"));
        users.addUser(new user("b", "b"));
        users.addUser(new user("c", "c"));
        users.addUser(new user("d", "d"));
        users.addUser(new user("e", "e"));

        users.deleteAll();

        ArrayList<user> userList = users.getAll();

        Assert.assertEquals(userList.size(), 0);
    }

    @Test

    public void canDeleteById() throws SQLException {

        user user1 = new user("a", "a");
        user user2 = new user("b", "b");
        user user3 = new user("c", "c");

        users.addUser(user1); //id: 1
        users.addUser(user2); //id: 2
        users.addUser(user3); //id: 3

        users.deleteById(2);

        ArrayList<user> userList2 = users.getAll();

        boolean deleted = false;

        if (!userList2.contains(user2)) {
            deleted = true;
        }

        Assert.assertEquals(deleted, true);
    }

}
