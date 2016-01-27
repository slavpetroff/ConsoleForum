package interfaces;

import interfaces.io.UserInput;
import interfaces.io.UserOutput;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Stanislav Petrov on 23.1.2016 г..
 */
public interface Engine {

    UserInput getInput();

    UserOutput getOutput();

    Database getDatabase();

    CommandManager getCommandManager();

    void setCommandManager(CommandManager commandManager);

    User getCurrentlyLoggedUser();

    void setCurrentlyLoggedUser(User currentliLoggedUser);

    void run() throws OperationNotSupportedException;
}
