package sk.fullstack.lany.server.model.DTO;

/**
 * Model, which holds data of user that wants to login.
 */
public class LoginModel {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
