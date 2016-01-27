package models.commands;

import interfaces.Database;
import interfaces.Engine;
import javax.naming.OperationNotSupportedException;

/**
 * Created by Stanislav Petrov on 24.1.2016 г..
 */
public abstract class Command implements interfaces.Command {

    protected String[] params;
    protected Database database;
    protected Engine engine;

    protected Command(String[] params, Database databaseParam, Engine engine) {
        // TODO separation
        this.setDatabase(databaseParam);
        this.params = params;
        this.engine = engine;
    }

    //region Properties
    private String[] getParams() {
        return params;
    }

    private void setParams(String[] params) {
        this.params = params;
    }

    private void setDatabase(Database databaseParam) {
        this.database = databaseParam;
    }
    //endregion

    @Override
    public abstract void execute() throws OperationNotSupportedException;
}
