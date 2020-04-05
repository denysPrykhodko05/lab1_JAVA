package lab3.controller;

import static java.lang.System.lineSeparator;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lab3.command.Command;
import lab3.command.impl.CreateStudentCommand;
import lab3.command.impl.ExitCommand;
import lab3.command.impl.IncorrectInputCommand;
import lab3.command.impl.ReadStudentCommand;
import lab3.util.ConsoleReader;
import org.apache.log4j.Logger;

public class FillFile {

    private static final Logger LOGGER = Logger.getLogger(FillFile.class);
    private static final Map<Integer, Command> commands = new HashMap<>();

    public static void main(String[] args) {
        int command = INTEGER_MINUS_ONE;
        fillCommands();

        while (command != 0) {
            LOGGER.info("Enter what do you want to do:" + lineSeparator()
                + "0 - Exit" + lineSeparator()
                + "1 - Create student" + lineSeparator()
                + "2 - Output students which have average mark greater than 4");

            try {
                command = ConsoleReader.readInt();
            } catch (IOException e) {
                LOGGER.info("Incorrect input. Try again!!");
            }
            commands.getOrDefault(command, new IncorrectInputCommand()).execute();
        }

    }

    private static void fillCommands() {
        commands.put(0, new ExitCommand());
        commands.put(1, new CreateStudentCommand());
        commands.put(2, new ReadStudentCommand());
    }

}
