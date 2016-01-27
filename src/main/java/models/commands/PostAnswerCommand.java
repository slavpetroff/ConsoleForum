package models.commands;

import interfaces.*;

import javax.naming.OperationNotSupportedException;
import java.util.Optional;

/**
 * Created by Stanislav Petrov on 26.1.2016 г..
 */
public class PostAnswerCommand extends Command {

    String body;

    public PostAnswerCommand(String[] params, Database databaseParam, Engine engine) {
        super(params, databaseParam, engine);
    }

    @Override
    public void execute() throws OperationNotSupportedException {
        this.checkForLoggedUser();

        this.body = this.params[1];

        Question questionToBeAddedPost = checkForOpenedQuestion();
        Answer answerToBeAdded = new models.Answer(this.engine.getCurrentlyLoggedUser(), this.body);
        questionToBeAddedPost.addAnswer(answerToBeAdded);
        this.engine.getOutput().write(String.format("Answer with Id: %d successfully posted",answerToBeAdded.getId()));
    }

    private Question checkForOpenedQuestion() throws OperationNotSupportedException {
        Question questionToBeReturned = null;

        Optional openedQuestion = this.engine.getDatabase()
                .getQuestions()
                .stream()
                .filter(Question::isOpened)
                .findFirst();

        if(openedQuestion.isPresent()) {
            questionToBeReturned = (Question) openedQuestion.get();
            return questionToBeReturned;
        } else {
            this.engine.getOutput().write("Operation not permitted. You have to open a question first");
            throw new OperationNotSupportedException("Operation not permitted. You have to open a question first");
        }
    }

    private void checkForLoggedUser() throws OperationNotSupportedException {
        if(this.engine.getCurrentlyLoggedUser() == null){
            this.engine.getOutput().write("Operation not permitted. You have to login first");
            throw new OperationNotSupportedException("Operation not permitted. You have to login first");
        }
    }
}
