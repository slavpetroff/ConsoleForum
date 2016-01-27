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
public class LoginTest {
    private ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    private Engine engine = context.getBean(models.Engine.class);
    private CommandManager commandManager = this.engine.getCommandManager();

    @Test
    public void testLogAnUser_ShouldBeLoggedInTheDatabase() throws Exception {
        this.initialise();

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

    @Test(expected = OperationNotSupportedException.class)
    public void testLogAnUserWhoIsAlreadyLoggedIn_ShouldBeLoggedInTheDatabase() throws Exception {
        this.initialise();

        String commandLineSecondLoggin = "Login Gosho 123";

        this.commandManager.setParams(commandLineSecondLoggin.split(" "));
        this.commandManager.executeCommand();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLogAnUserWhoDoesNotExist_ShouldThrowException() throws Exception {
        String commandLine = "Register Gosho 123 abv.bg";
        String commandLineLoggin = "Login Gosho123 123";

        this.commandManager.setParams(commandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(commandLineLoggin.split(" "));
        this.commandManager.executeCommand();
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