package models.commands;

import interfaces.Database;
import interfaces.Engine;
import interfaces.Question;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

/**
 * Created by Stanislav Petrov on 25.1.2016 г..
 */
public class PostQuestionCommand extends Command {

    public PostQuestionCommand(String[] params, Database databaseParam, Engine engine) {
        super(params, databaseParam, engine);
    }

    @Override
    public void execute() throws OperationNotSupportedException {
        String title = this.params[1];
        String body = this.params[2];
        boolean hasLoggedUsers = !this.engine.getDatabase().getCurrentlyLoggedUsers().isEmpty();
        if(hasLoggedUsers) {
            Question questionToBeAdded = new models.Question(title, body, new ArrayList<>());
            questionToBeAdded.setId(this.engine.getDatabase().getQuestions().size() + 1);
            questionToBeAdded.setAuthor(this.engine.getCurrentlyLoggedUser());
            this.engine.getDatabase().getQuestions().add(questionToBeAdded);
            this.engine.getOutput().write(String.format("Question with Id: %d successfully posted", questionToBeAdded
                    .getId()));
        } else {
            this.engine.getOutput().write("Operation not permitted. You have to login first");
            throw new OperationNotSupportedException("Operation not permitted. You have to login first");
        }
    }
}
