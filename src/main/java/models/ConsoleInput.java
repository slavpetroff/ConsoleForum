package models;

import interfaces.io.UserInput;

import java.util.Scanner;

/**
 * Created by Stanislav Petrov on 24.1.2016 г..
 */
public class ConsoleInput implements UserInput {

    @Override
    public String read() {
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        return result;
    }
}
