package sudokuApp;

import java.sql.*;
import java.util.Objects;

public class user {

    private String userName;
    private String pswd;

    public user(String userName, String pswd) {
        this.userName = userName;
        this.pswd = pswd;
    }

    public String getUserName() {
        return userName;
    }

    public String getPswd() {
        return pswd;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Override
    public String toString() {
        return "Name: " + userName + ", password: " + pswd;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    public boolean equals(user u) {
        if (this.userName.equals(u.getUserName()) && this.pswd.equals(u.getPswd())) {
            return true;
        }
        return false;
    }

}
