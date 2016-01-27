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
public class PostQuestionTestMORETESTS {

    private ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    private Engine engine = context.getBean(models.Engine.class);
    private CommandManager commandManager = this.engine.getCommandManager();

    @Test
    public void testPostQuestion_ShouldHasTheQuestionInDatabase() throws OperationNotSupportedException {
        this.initialise();

        String postQuestionCommandLine = "PostQuestion Help HomeworkProblems";

        this.commandManager.setParams(postQuestionCommandLine.split(" "));
        this.commandManager.executeCommand();

        Assert.assertTrue(this.engine.getDatabase().getQuestions().stream().anyMatch(x -> {
            if(x.getTitle().contentEquals(postQuestionCommandLine.split(" ")[1])) {
                return true;
            }

            return false;
        }));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testPostQuestionIfNoLoggedUsers_ShouldThrowException() throws OperationNotSupportedException {
        String postQuestionCommandLine = "PostQuestion Help HomeworkProblems";

        this.commandManager.setParams(postQuestionCommandLine.split(" "));
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
