package lab3.command.impl;

import lab3.command.Command;
import org.apache.log4j.Logger;

public class ExitCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ExitCommand.class);

    @Override
    public void execute() {
        LOGGER.info("Bye, bye, bye");
    }
}
