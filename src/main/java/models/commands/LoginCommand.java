package models.commands;

import interfaces.Database;
import interfaces.Engine;
import interfaces.User;

import javax.naming.OperationNotSupportedException;
import java.util.Optional;

/**
 * Created by Stanislav Petrov on 24.1.2016 г..
 */
public class LoginCommand extends Command {

    private String username;
    private String password;

    public LoginCommand(String[] params, Database databaseParam, Engine engine) {
        super(params, databaseParam, engine);
        this.username = this.params[1];
        this.password = this.params[2];
    }

    @Override
    public void execute() throws OperationNotSupportedException {
        User userToLog = this.userToLogin();
        if(userToLog.isLogged()){
            this.engine.getOutput().write("Already logged in. In order to switch to another user - logout first");
            throw new OperationNotSupportedException("Already logged in. In order to switch to another user - logout first");
        }

        userToLog.setLogged(true);
        this.engine.setCurrentlyLoggedUser(userToLog);
        this.engine.getOutput()
                .write(String.format("User %s with Id: %d successfully logged in",
                        userToLog.getUsername(),
                        userToLog.getId()));
    }

    private User userToLogin() {
        Optional<User> userToCheck = this.engine.getDatabase()
                .getUsers()
                .stream()
                .filter(user -> {
                    boolean validUsername = user.getUsername().contentEquals(this.username);
                    boolean validPassword = user.getPassword().contentEquals(this.password);
                    return validUsername && validPassword;
                })
                .findFirst();

        if (!userToCheck.isPresent()) {
            this.engine.getOutput().write("Wrong username/password or username not registered");
            throw new IllegalArgumentException("Wrong username/password or username not registered");
        }

        return userToCheck.get();
    }
}
