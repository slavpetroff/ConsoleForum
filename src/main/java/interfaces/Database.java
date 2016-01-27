package interfaces;

import java.util.Collection;

/**
 * Created by Stanislav Petrov on 23.1.2016 г..
 */
public interface Database {

    Collection<User> getUsers();

    Admin getAdmin();

    void setAdmin(Admin admin);

    Collection<User> getCurrentlyLoggedUsers();

    Collection<Question> getQuestions() ;

    Collection<Post> getPosts();
}
