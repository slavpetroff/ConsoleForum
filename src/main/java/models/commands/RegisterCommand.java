package models.commands;

import interfaces.Admin;
import interfaces.Database;
import interfaces.Engine;
import interfaces.User;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Stanislav Petrov on 24.1.2016 г..
 */
public class RegisterCommand extends Command {

    public RegisterCommand(String params[], Database databaseParam, Engine engine) {
        super(params, databaseParam, engine);
    }

    @Override
    public void execute() throws OperationNotSupportedException {
        if(!isAdministrator()) {
            User createdUser = this.createUser();

            boolean validUsername = isValidUsername(createdUser.getUsername());
            boolean validEmail = isValidEmail(createdUser.getEmail());
            if(validUsername && validEmail) {
                createdUser.setId(this.engine.getDatabase().getUsers().size()+1);
                this.engine.getDatabase().getUsers().add(createdUser);
                this.engine.getOutput().write(String.format("User %s successfully registered", createdUser
                        .getUsername()));

            } else {
                this.engine.getOutput().write("User with the same mail or username already exists");
                throw new OperationNotSupportedException("User with the same mail or username already exists");
            }
        } else {
            createAdmin();
        }
    }

    private void createAdmin() throws OperationNotSupportedException {
        Admin adminToTest = this.engine.getDatabase().getAdmin();
        if(adminToTest != null) {
            this.engine.getOutput().write("Cannot register administrator");
            throw new OperationNotSupportedException("Cannot register administrator");
        }

        if(this.params[4].contentEquals("administrator")
                && this.engine.getDatabase().getUsers().isEmpty()) {
            String email = this.params[3];
            String password = this.params[2];
            String username = this.params[1];
            Admin admin = new models.Admin(username, password, email);
            this.engine.getDatabase().setAdmin(admin);
        }
    }

    private User createUser() {
        String email = this.params[3];
        String password = this.params[2];
        String username = this.params[1];

        User userToBeReturned = new models.User(username, password, email);
        return userToBeReturned;
    }

    private boolean isAdministrator() {
        return this.params.length > 4;
//        return this.params[4].equalsIgnoreCase("administrator");
    }

    private boolean isValidUsername(String username) {
        boolean result = !this.engine.getDatabase().getUsers().stream()
                .anyMatch(x -> x.getUsername().contentEquals(username));
        return result;
    }

    private boolean isValidEmail(String email) {
        boolean result = !this.engine.getDatabase().getUsers()
                .stream()
                .anyMatch(x -> x.getEmail().contentEquals(email));
        return result;
    }
}
