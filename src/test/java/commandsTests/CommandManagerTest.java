package commandsTests;

import configFiles.MainConfig;
import interfaces.CommandManager;
import interfaces.Engine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Created by Stanislav Petrov on 24.1.2016 г..
 */
@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(models.Engine.class)
public class CommandManagerTest {
    private ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    private Engine engine = context.getBean(models.Engine.class);
    private CommandManager commandManager = engine.getCommandManager();

    @Mock
    private Engine myEngine;

    @Test
    public void testConstructor_ShouldNotBeNull() throws Exception {
        Assert.assertNotNull(this.commandManager);
    }

    @Test
    public void testExecuteCommand_RegisterCommand_UsersInDatabaseShouldNotBeEmpty() throws Exception {
        String commandLine = "Register Gosho 123 abv.bg";

        this.commandManager.setParams(commandLine.split(" "));
        this.commandManager.executeCommand();

        Assert.assertTrue(!this.engine.getDatabase().getUsers().isEmpty());
    }
}