package models.commands;

import interfaces.Database;
import interfaces.Engine;
import interfaces.Question;

import javax.naming.OperationNotSupportedException;
import java.util.Optional;

/**
 * Created by Stanislav Petrov on 26.1.2016 г..
 */
public class OpenQuestionCommand extends Command {

    private int idOfTheQuestion;

    public OpenQuestionCommand(String[] params, Database databaseParam, Engine engine) {
        super(params, databaseParam, engine);
    }

    @Override
    public void execute() throws OperationNotSupportedException {
        Question question = findQuestion();
        question.setOpened(true);
        this.engine.getOutput().write(question.toString());
    }

    private Question findQuestion() {
        this.idOfTheQuestion = Integer.parseInt(this.params[1]);
        Optional result = this.engine.getDatabase()
                .getQuestions()
                .stream()
                .filter(q -> q.getId() == this.idOfTheQuestion)
                .findFirst();

        if(result.isPresent()) {
            return (Question) result.get();
        } else {
            this.engine.getOutput().write("No such question");
            throw new IllegalArgumentException("No such question");
        }
    }
}
