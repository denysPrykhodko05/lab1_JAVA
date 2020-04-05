package lab3.command.impl;

import lab3.command.Command;
import org.apache.log4j.Logger;

public class IncorrectInputCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(IncorrectInputCommand.class);

    @Override
    public void execute() {
        LOGGER.info("Incorrect input!!!");
    }
}
