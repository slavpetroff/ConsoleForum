package commandsTests;

import configFiles.MainConfig;
import interfaces.CommandManager;
import interfaces.Engine;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Stanislav Petrov on 25.1.2016 г..
 */
public class LogoutTest {
    private ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    private Engine engine = context.getBean(models.Engine.class);
    private CommandManager commandManager = this.engine.getCommandManager();

    @Test
    public void testLogoutAnUser_ShouldBeLoggedOutInTheDatabase() throws Exception {
        this.initialise();

        String logoutCommandLine = "Logout Gosho 123";

        this.commandManager.setParams(logoutCommandLine.split(" "));
        this.commandManager.executeCommand();

        Assert.assertTrue(this.engine.getDatabase()
                .getUsers()
                .stream()
                .anyMatch(user -> {
                    boolean validUsername = user.getUsername().contentEquals("Gosho");
                    boolean validPassword = user.getPassword().contentEquals("123");
                    if(validUsername && validPassword && !user.isLogged()) {
                        return true;
                    }

                    return false;
                }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLogoutAnUserWhoDoesNotExist_ShouldThrowException() throws Exception {
        this.initialise();

        String logoutCommandLine = "Logout Gosho123 123";

        this.commandManager.setParams(logoutCommandLine.split(" "));
        this.commandManager.executeCommand();

        Assert.assertTrue(this.engine.getDatabase()
                .getUsers()
                .stream()
                .anyMatch(user -> {
                    boolean validUsername = user.getUsername().contentEquals("Gosho");
                    boolean validPassword = user.getPassword().contentEquals("123");
                    if(validUsername && validPassword && user.isLogged()) {
                        return true;
                    }

                    return false;
                }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLogoutAnUserWhoIsNotLoggedIn_ShouldThrowException() throws Exception {
        String registerCommandLine = "Register Gosho 123 abv.bg";
        String logoutCommandLine = "Logout Gosho 123";

        this.commandManager.setParams(registerCommandLine.split(" "));
        this.commandManager.executeCommand();
        this.commandManager.setParams(logoutCommandLine.split(" "));
        this.commandManager.executeCommand();

        Assert.assertTrue(this.engine.getDatabase()
                .getUsers()
                .stream()
                .anyMatch(user -> {
                    boolean validUsername = user.getUsername().contentEquals("Gosho");
                    boolean validPassword = user.getPassword().contentEquals("123");
                    if(validUsername && validPassword && user.isLogged()) {
                        return true;
                    }

                    return false;
                }));
    }

    private void initialise() throws OperationNotSupportedException {
        String registerCommandLine = "Register Gosho 123 abv.bg";
        String loginCommandLine = "Login Gosho 123";
        this.commandManager.setParams(registerCommandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(loginCommandLine.split(" "));
        this.commandManager.executeCommand();
    }
}