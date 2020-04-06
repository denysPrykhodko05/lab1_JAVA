package lab4.command.impl;

import lab4.command.BrandCommand;
import org.apache.log4j.Logger;

public class ExitBrandCommandImpl implements BrandCommand {

    private static final Logger LOGGER = Logger.getLogger(ExitBrandCommandImpl.class);

    @Override
    public void execute() {
        LOGGER.info("Bye, bye, bye");
    }
}
