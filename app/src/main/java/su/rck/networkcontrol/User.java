package su.rck.networkcontrol;

/**
 * Created by Александр on 06.09.2017.
 */

public class User {
    int uID;
    String uLogin;
    String uPassword;
    String uName;
    String uSurname;

    public User() {

    }

    public User(String login, String password, String name, String surname) {
        uLogin = login;
        uPassword = password;
        uName = name;
        uSurname = surname;
    }

    public int getID() {
        return uID;
    }

    public void setID(int uID) {
        this.uID = uID;
    }

    public String getLogin() {
        return uLogin;
    }

    public void setLogin(String uLogin) {
        this.uLogin = uLogin;
    }

    public String getPassword() {
        return uPassword;
    }

    public void setPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getName() {
        return uName;
    }

    public void setName(String uNname) {
        this.uName = uNname;
    }

    public String getSurname() {
        return uSurname;
    }

    public void setSsurname(String uSsurname) {
        this.uSurname = uSsurname;
    }
}
