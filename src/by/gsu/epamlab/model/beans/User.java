package by.gsu.epamlab.model.beans;

public class User {
    private int userId;
    private String login;

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    public User(int id, String login) {
        this.userId = id;
        this.login = login;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
