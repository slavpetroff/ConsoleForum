package commandsTests;

import configFiles.MainConfig;
import interfaces.CommandManager;
import interfaces.Engine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Stanislav Petrov on 26.1.2016 г..
 */
@RunWith(MockitoJUnitRunner.class)
@PrepareForTest({models.Engine.class,CommandManagerTest.class})
public class OpenQuestionMORETESTS {

    private ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    private Engine engine = context.getBean(models.Engine.class);
    private CommandManager commandManager = this.engine.getCommandManager();

    @Test
    public void testOpenQuestion_ShouldWriteOnConsole() throws OperationNotSupportedException {
        this.initialise();

        String postQuestionCommandLine = "PostQuestion Help HomeworkProblems";
        String openQuestionCommandLine = "OpenQuestion 1";

        this.commandManager.setParams(postQuestionCommandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(openQuestionCommandLine.split(" "));
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
