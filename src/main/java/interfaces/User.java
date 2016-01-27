package interfaces;

import enums.ForumRights;

/**
 * Created by Stanislav Petrov on 23.1.2016 г..
 */
public interface User {

    ForumRights getUserRights();

    String getName();

    String getPassword();

    String getEmail();

    int getId();

    void setId(int id);

    String getUsername();

    boolean isLogged();

    void setLogged(boolean logged);
}
