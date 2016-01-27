package models;

import interfaces.Admin;
import interfaces.Post;
import interfaces.Question;
import interfaces.User;

import java.util.Collection;

/**
 * Created by Stanislav Petrov on 24.1.2016 г..
 */
public class Database implements interfaces.Database {

    private Collection<User> users;
    private Collection<User> currentlyLoggedUsers;
    private Collection<Question> questions;
    private Collection<Post> posts;
    private Admin admin;

    public Database(Collection<User> users,
                    Collection<User> currentlyLoggedUsers,
                    Collection<Question> questions,
                    Collection<Post> posts) {
        this.setUsers(users);
        this.setCurrentlyLoggedUsers(currentlyLoggedUsers);
        this.setQuestions(questions);
        this.setPosts(posts);
    }

    //region Properties
    public Collection<Question> getQuestions() {
        return questions;
    }

    private void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    private void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    //TODO not tested !
    public Collection<User> getCurrentlyLoggedUsers() {
        this.users.stream().filter(User::isLogged).findAny().ifPresent(e -> currentlyLoggedUsers.add(e));
        return this.currentlyLoggedUsers;
    }

    private void setCurrentlyLoggedUsers(Collection<User> currentlyLoggedUsers) {
        this.currentlyLoggedUsers = currentlyLoggedUsers;
    }

    @Override
    public Collection<User> getUsers() {
        return users;
    }

    private void setUsers(Collection<User> users) {
        this.users = users;
    }
    //endregion
}
