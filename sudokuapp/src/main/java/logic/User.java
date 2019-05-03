package logic;

/**
 *
 * @author hepulli
 */
public class User {

    private int id;
    private String userName;
    private String pswd;

    /**
     * User -olion luova konstruktori. parametrina annetaan nimi ja salasana.
     *
     * @param userName käyttäjänimi
     * @param pswd salasana
     */
    public User(String userName, String pswd) {
        this.userName = userName;
        this.pswd = pswd;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPswd() {
        return pswd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Override
    public String toString() {
        return "id: " + id + ", Name: " + userName + ", password: " + pswd;
    }

    public boolean equals(User u) {
        if (this.userName.equals(u.getUserName()) && this.pswd.equals(u.getPswd())) {
            return true;
        }
        return false;
    }

}
