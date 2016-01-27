package models;

import enums.ForumRights;

/**
 * Created by Stanislav Petrov on 23.1.2016 г..
 */
public class User implements interfaces.User {

    private ForumRights userRights;
    private String username;
    private String name;
    private String password;
    private String email;
    private int id;
    private boolean logged;

    public User(String username, String password,String email ) {
        this.setEmail(email);
        this.setPassword(password);
        this.setUsername(username);
        this.setUserRights(ForumRights.read);
        this.setLogged(false);
    }

    //region Properties

    @Override
    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    @Override
    public ForumRights getUserRights() {
        return userRights;
    }

    private void setUserRights(ForumRights userRights) {
        this.userRights = userRights;
    }

    @Override
    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }
    //endregion

    @Override
    public boolean equals(Object obj) {
        // TODO Add id case ! !
        interfaces.User expectedUser = (User) obj;
        if(!this.getName().contentEquals(expectedUser.getName())){
            return false;
        }
        if(!this.getPassword().contentEquals(expectedUser.getPassword())){
            return false;
        }
        if(!this.getEmail().contentEquals(expectedUser.getEmail())){
            return false;
        }
            return true;
    }

    public void logUser(){
        // TODO implement logIn
    }
}
