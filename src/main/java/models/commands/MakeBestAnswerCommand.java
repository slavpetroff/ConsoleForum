package models.commands;

import interfaces.Answer;
import interfaces.Database;
import interfaces.Engine;
import interfaces.Question;

import javax.naming.OperationNotSupportedException;
import java.util.Optional;

/**
 * Created by Stanislav Petrov on 27.1.2016 г..
 */
public class MakeBestAnswerCommand extends Command {

    private int idOfTheAnswer;

    public MakeBestAnswerCommand(String[] params, Database databaseParam, Engine engine) {
        super(params, databaseParam, engine);

        this.idOfTheAnswer = Integer.parseInt(this.params[1]);
    }

    @Override
    public void execute() throws OperationNotSupportedException {
        this.validateOperation();

        Answer answerToMakeBest = this.findQuestion()
                .getAnswers()
                .stream()
                .filter(a -> a.getId() == this.idOfTheAnswer)
                .findFirst()
                .get();

        answerToMakeBest.setAsBestAnswer(true);
    }

    private void validateOperation() throws OperationNotSupportedException {
        Question questionToValidate = this.findQuestion();

        boolean isAuthorValid = questionToValidate.getAuthor()
                .getUsername()
                .contentEquals(this.engine.getCurrentlyLoggedUser().getUsername());

        if(isAuthorValid) {
            this.validateUserIsLogged();

            this.validateQuestionOpened();

        } else {
            this.engine.getOutput().write("You do not have enough permission to perform the desired operation");
            throw new OperationNotSupportedException("You do not have enough permission to perform the desired " +
                    "operation");
        }
    }

    private Question findQuestion() throws OperationNotSupportedException {
        Optional presentQuestion = this.engine.getDatabase()
                .getQuestions()
                .stream()
                .filter(q -> q.getAnswers()
                        .stream()
                        .anyMatch(a -> a.getId() == this.idOfTheAnswer))
                .findFirst();

        if(presentQuestion.isPresent()) {
            return (Question) presentQuestion.get();
        } else {
            this.engine.getOutput().write("No such answer");
            throw new IllegalArgumentException("No such answer");
        }
    }

    private void validateUserIsLogged() throws OperationNotSupportedException {
        if(this.engine.getCurrentlyLoggedUser() == null) {
            this.engine.getOutput().write("Operation not permitted. You have to login first");
            throw new OperationNotSupportedException("Operation not permitted. You have to login first");
        }
    }

    private void validateQuestionOpened() throws OperationNotSupportedException {
        boolean isQuestionOpened = this.engine.getDatabase()
                .getQuestions()
                .stream()
                .anyMatch(Question::isOpened);

        if(!isQuestionOpened) {
            this.engine.getOutput().write("Operation not permitted. You have to open a question first");
            throw new OperationNotSupportedException("Operation not permitted. You have to open a question first");
        }
    }
}
