package models;

import interfaces.io.UserOutput;

/**
 * Created by Stanislav Petrov on 24.1.2016 г..
 */
public class ConsoleOutput implements UserOutput {

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
