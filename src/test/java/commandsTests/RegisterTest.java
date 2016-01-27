package commandsTests;

import configFiles.MainConfig;
import interfaces.CommandManager;
import interfaces.Engine;
import interfaces.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Stanislav Petrov on 25.1.2016 г..
 */
public class RegisterTest {
    private ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    private Engine engine = context.getBean(models.Engine.class);
    private CommandManager commandManager = this.engine.getCommandManager();

    @Test
    public void testExecuteCommand_RegisterCommand_ShouldCreateUserCorrectly() throws Exception {
        String commandLine = "Register Gosho 123 abv.bg";
        User expectedUser = new models.User("Gosho", "123", "abv.bg");

        this.commandManager.setParams(commandLine.split(" "));
        this.commandManager.executeCommand();

        // TODO need a lot of optimizations ! ! !
        User actualUser = this.engine.getDatabase().getUsers()
                .stream()
                .filter(x -> x.getUsername().contentEquals(expectedUser.getUsername()))
                .findFirst()
                .get();

        // TODO Override Equals of the users ! ! !
        boolean passEquals = actualUser.getPassword().contentEquals(expectedUser.getPassword());
        boolean emailEquals = actualUser.getEmail().contentEquals(expectedUser.getEmail());

        /* We compare to (actualUser.getId() - 1), because when the engine registers an user,
           engine's id is already incremented */
        boolean idEquals = (actualUser.getId() - 1) == expectedUser.getId();
        Assert.assertTrue(passEquals && emailEquals && idEquals);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testExecuteCommand_RegisterSameUsername_ShouldThrowException() throws Exception {
        String commandLine = "Register Gosho 123 abv.bg";
        String secondLine = "Register Gosho 153 aaa.bg";

        this.commandManager.setParams(commandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(secondLine.split(" "));
        this.commandManager.executeCommand();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testExecuteCommand_RegisterSameEmail_ShouldThrowException() throws Exception {
        String commandLine = "Register Gosho 123 abv.bg";
        String secondLine = "Register Gosho123 153 abv.bg";

        this.commandManager.setParams(commandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(secondLine.split(" "));
        this.commandManager.executeCommand();
    }

    @Test
    public void testExecuteCommand_RegisterAdminCommand_ShouldCreateAdminCorrectly() throws Exception {
        String commandLine = "Register Gosho 123 abv.bg administrator";

        this.commandManager.setParams(commandLine.split(" "));
        this.commandManager.executeCommand();

        Assert.assertNotNull(this.engine.getDatabase().getAdmin());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testExecuteCommand_RegisterAdminIfAlreadyHasAdmin_ShouldThrowException() throws Exception {
        String commandLine = "Register Gosho 123 abv.bg administrator";
        String secondCommandLine = "Register Admin 123 abv.bg administrator";

        this.commandManager.setParams(commandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(secondCommandLine.split(" "));
        this.commandManager.executeCommand();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testExecuteCommand_RegisterWithInvalidCommand_ShouldThrowException() throws Exception {
        String commandLine = "sad Gosho 123 abv.bg administrator";

        this.commandManager.setParams(commandLine.split(" "));
        this.commandManager.executeCommand();
    }
}