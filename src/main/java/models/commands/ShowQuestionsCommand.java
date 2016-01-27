package models.commands;

import interfaces.Database;
import interfaces.Engine;

/**
 * Created by Stanislav Petrov on 25.1.2016 г..
 */
public class ShowQuestionsCommand extends Command {

    public ShowQuestionsCommand(String[] params, Database databaseParam, Engine engine) {
        super(params, databaseParam, engine);
    }

    @Override
    public void execute() {
        boolean haveQuestions = this.isThereQuestions();
        if(haveQuestions) {
            this.engine.getDatabase()
                    .getQuestions()
                    .stream()
                    .forEach(question -> this.engine.getOutput()
                            .write(String.format("%s%s",
                                    question.toString(),
                                    System.getProperty("line.separator"))));
        } else {
            this.engine.getOutput().write("No questions");
            throw new IllegalArgumentException("No questions");
        }

    }

    private boolean isThereQuestions() {
        boolean result = false;
        result = !this.engine.getDatabase().getQuestions().isEmpty();
        return result;
    }
}
