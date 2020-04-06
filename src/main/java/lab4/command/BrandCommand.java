package lab4.command;

import java.io.IOException;
import lab3.util.ConsoleReader;
import lab4.command.impl.DeleteBrandCommandImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public interface BrandCommand {

    void execute();

    default String fillName() {
        Logger LOGGER = Logger.getLogger(DeleteBrandCommandImpl.class);
        String name = StringUtils.EMPTY;

        while (StringUtils.EMPTY.equals(name)) {
            try {
                LOGGER.info("Enter name:");
                name = ConsoleReader.readLine();
            } catch (IOException e) {
                LOGGER.error("Incorrect input. Try again!!!");
            }
        }

        return name;
    }
}
