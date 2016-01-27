package models;

import interfaces.CommandManager;
import interfaces.Database;
import interfaces.User;
import interfaces.io.UserInput;
import interfaces.io.UserOutput;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Stanislav Petrov on 24.1.2016 г..
 */
public class Engine implements interfaces.Engine {

    private UserInput input;
    private UserOutput output;
    private Database database;
    private CommandManager commandManager;
    private User currentlyLoggedUser;

    public Engine(UserInput input,
                  UserOutput output,
                  Database database,
                  CommandManager commandManager) {
        this.setInput(input);
        this.setOutput(output);
        this.setDatabase(database);
        this.setCommandManager(commandManager);
    }

    //region Properties
    public User getCurrentlyLoggedUser() {
        return currentlyLoggedUser;
    }

    public void setCurrentlyLoggedUser(User currentlyLoggedUser) {
        this.currentlyLoggedUser = currentlyLoggedUser;
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public Database getDatabase() {
        return database;
    }

    private void setDatabase(Database database) {
        this.database = database;
    }

    @Override
    public UserInput getInput() {
        return input;
    }

    private void setInput(UserInput input) {
        this.input = input;
    }

    @Override
    public UserOutput getOutput() {
        return output;
    }

    private void setOutput(UserOutput output) {
        this.output = output;
    }
    //endregion

    @Override
    public void run() throws OperationNotSupportedException {
        while (true){
            this.commandManager.readCommand();
            this.commandManager.executeCommand();
        }
    }
}
