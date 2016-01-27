package interfaces;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Stanislav Petrov on 23.1.2016 г..
 */
public interface Command {

    void execute() throws OperationNotSupportedException;
}
