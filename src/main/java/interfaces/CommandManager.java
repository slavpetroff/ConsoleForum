package interfaces;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Stanislav Petrov on 23.1.2016 г..
 */
public interface CommandManager {

    Engine getEngine();

    void setEngine(Engine engine);

    String[] getParams();

    void setParams(String[] params);

    void readCommand();

    void executeCommand() throws OperationNotSupportedException;
}
