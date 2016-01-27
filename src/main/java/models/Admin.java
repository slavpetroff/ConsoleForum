package models;

/**
 * Created by Stanislav Petrov on 24.1.2016 г..
 */
public class Admin implements interfaces.Admin {
    private String username;
    private String name;
    private String password;
    private String email;

    public Admin(String username, String password, String email) {
        this.setEmail(email);
        this.setPassword(password);
        this.setUsername(username);
    }

    //region Propeerties
    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }
    //endregion
}
