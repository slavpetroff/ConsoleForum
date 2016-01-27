package models;

import interfaces.Database;
import interfaces.Engine;
import models.commands.*;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Stanislav Petrov on 24.1.2016 г..
 */
public class CommandManager implements interfaces.CommandManager {

    // SETTER INJECTION FOR THE ENGINE ! ! !
    private Engine engine;
    private Database database;
    private String[] params;

    //region Properties
    public void setParams(String[] params){
        this.params = params;
    }

    public String[] getParams() {
        return params;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
        this.database = engine.getDatabase();
    }
    //endregion

    public void readCommand() {
        this.params = this.getEngine().getInput().read().split(" ");
    }

    public void executeCommand() throws OperationNotSupportedException {
        String actualCommand = this.params[0];

        // TODO Add commands here
        switch (actualCommand.toLowerCase()){
            case "register" :
                new RegisterCommand(this.params, this.database, this.getEngine()).execute();
                break;
            case "login" :
                new LoginCommand(this.params, this.database, this.getEngine()).execute();
                break;
            case "logout" :
                new LogoutCommand(this.params, this.database, this.getEngine()).execute();
                break;
            case "showquestions" :
                new ShowQuestionsCommand(this.params, this.database, this.getEngine()).execute();
                break;
            case "postquestion" :
                new PostQuestionCommand(this.params, this.database, this.getEngine()).execute();
                break;
            case "postanswer" :
                new PostAnswerCommand(this.params, this.database, this.getEngine()).execute();
                break;
            case "openquestion" :
                new OpenQuestionCommand(this.params, this.database, this.getEngine()).execute();
                break;
            case "makebestanswer" :
                new MakeBestAnswerCommand(this.params, this.database, this.getEngine()).execute();
                break;
            default:
                throw new OperationNotSupportedException("Command was not found in the Commands collection ! !");
        }
    }
}
