package models.commands;

import interfaces.Database;
import interfaces.Engine;
import interfaces.User;

/**
 * Created by Stanislav Petrov on 25.1.2016 г..
 */
public class LogoutCommand extends Command {

    public LogoutCommand(String[] params, Database databaseParam, Engine engine) {
        super(params, databaseParam, engine);
    }

    @Override
    public void execute() throws IllegalArgumentException {
        User userToLogout = this.engine.getCurrentlyLoggedUser();
        if(userToLogout.isLogged()){
            userToLogout.setLogged(false);
            this.engine.setCurrentlyLoggedUser(null);
            this.engine.getOutput().write("LogoutCommand success");
        } else {
            this.engine.getOutput().write("Operation not permitted. You have to login first");
            throw new IllegalArgumentException("Operation not permitted. You have to login first");
        }

    }
}
