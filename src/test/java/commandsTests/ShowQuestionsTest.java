package commandsTests;

import configFiles.MainConfig;
import interfaces.CommandManager;
import interfaces.Engine;
import interfaces.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Stanislav Petrov on 25.1.2016 г..
 */
@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(User.class)
public class ShowQuestionsTest {

    @Mock
    private User mockedUser;

    private ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    private Engine engine = context.getBean(models.Engine.class);
    private CommandManager commandManager = this.engine.getCommandManager();

    @Test
    public void testShowQuestions_QuestionAskedShouldBeTheSameInDatabase() throws Exception {
        this.initialise();

        String commandLine = "ShowQuestions";

        Mockito.when(mockedUser.getUsername()).thenReturn("Gosho");
        Mockito.when(mockedUser.getPassword()).thenReturn("123");
        Mockito.when(mockedUser.isLogged()).thenReturn(true);

        this.commandManager.setParams(commandLine.split(" "));
        this.commandManager.executeCommand();

        Assert.assertTrue(this.engine.getDatabase()
                .getQuestions()
                .stream()
                .anyMatch(x -> {
                    boolean matchTitle = x.getTitle().contentEquals("Help");

                    boolean matchBody = x.getBody().contentEquals("HomeworkProblems");

                    boolean authorUsernameMatches = x.getAuthor()
                            .getUsername()
                            .contentEquals(mockedUser.getUsername());

                    boolean authorPasswordMatches = x.getAuthor()
                            .getPassword()
                            .contentEquals(mockedUser.getPassword());

                    return matchBody && matchTitle && authorPasswordMatches && authorUsernameMatches;

                }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShowQuestionsWithNoQuestions_ShouldThrowException() throws Exception {
        String showQuestionsCommandLine = "ShowQuestions";

        this.commandManager.setParams(showQuestionsCommandLine.split(" "));
        this.commandManager.executeCommand();
    }

    private void initialise() throws OperationNotSupportedException {
        String registerCommandLine = "Register Gosho 123 abv.bg";
        String loginCommandLine = "Login Gosho 123";
        String postQuestionCommandLine = "PostQuestion Help HomeworkProblems";

        this.commandManager.setParams(registerCommandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(loginCommandLine.split(" "));
        this.commandManager.executeCommand();

        this.commandManager.setParams(postQuestionCommandLine.split(" "));
        this.commandManager.executeCommand();
    }
}