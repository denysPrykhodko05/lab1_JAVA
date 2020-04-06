package lab4.command.impl;

import lab4.command.BrandCommand;
import org.apache.log4j.Logger;

public class IncorrectInputBrandCommandImpl implements BrandCommand {

    private static final Logger LOGGER = Logger.getLogger(IncorrectInputBrandCommandImpl.class);

    @Override
    public void execute() {
        LOGGER.info("Incorrect input try again");
    }
}
