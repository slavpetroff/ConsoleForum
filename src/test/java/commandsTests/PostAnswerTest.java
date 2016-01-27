package commandsTests;

import configFiles.MainConfig;
import interfaces.CommandManager;
import interfaces.Engine;
import interfaces.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
public class PostAnswerTest {

    private ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    private Engine engine = context.getBean(models.Engine.class);
    private CommandManager commandManager = this.engine.getCommandManager();

    @Mock
    private User user;

    @Test
    public void testPostAnswer_ShouldExistsInDatabase() throws OperationNotSupportedException {
        this.initialise();

        String postAnswerCommandLine = "PostAnswer daaaa@@@12121@";

        this.commandManager.setParams(postAnswerCommandLine.split(" "));
        this.commandManager.executeCommand();

        Assert.assertTrue(this.engine.getDatabase()
                .getQuestions()
                .stream()
                .anyMatch(x -> !x.getAnswers().isEmpty()));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testPostAnswerNotLoggedIn_ShouldThrowException() throws OperationNotSupportedException {
        this.initialise();

        String logoutCommandLine = "Logout Gosho 123";
        String postAnswerCommandLine = "PostAnswer daaaa@@@12121@";

        this.commandManager.setParams(logoutCommandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(postAnswerCommandLine.split(" "));
        this.commandManager.executeCommand();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testPostAnswerNoOpenedQuestion_ShouldThrowException() throws OperationNotSupportedException {
        String registerCommandLine = "Register Gosho 123 abv.bg";
        String loginCommandLine = "Login Gosho 123";
        String postQuestionCommandLine = "PostQuestion Help HomeworkProblems";
        String postAnswerCommandLine = "PostAnswer daaaa@@@12121@";

        this.commandManager.setParams(registerCommandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(loginCommandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(postQuestionCommandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(postAnswerCommandLine.split(" "));
        this.commandManager.executeCommand();
    }

    private void initialise() throws OperationNotSupportedException {
        String registerCommandLine = "Register Gosho 123 abv.bg";
        String loginCommandLine = "Login Gosho 123";
        String postQuestionCommandLine = "PostQuestion Help HomeworkProblems";
        String openQuestionCommandLine = "OpenQuestion 1";

        this.commandManager.setParams(registerCommandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(loginCommandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(postQuestionCommandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(openQuestionCommandLine.split(" "));
        this.commandManager.executeCommand();
    }
}
