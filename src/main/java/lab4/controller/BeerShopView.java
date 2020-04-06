package lab4.controller;

import static java.lang.System.lineSeparator;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lab3.util.ConsoleReader;
import lab4.command.BrandCommand;
import lab4.command.impl.AddBrandCommandImpl;
import lab4.command.impl.DeleteBrandCommandImpl;
import lab4.command.impl.ExitBrandCommandImpl;
import lab4.command.impl.GetBrandCommandImpl;
import lab4.command.impl.IncorrectInputBrandCommandImpl;
import lab4.service.BrandService;
import lab4.service.brandserviceimpl.BrandServiceImpl;
import org.apache.log4j.Logger;

public class BeerShopView {

    private static final Logger LOGGER = Logger.getLogger(BeerShopView.class);

    public static void main(String[] args) {
        Map<Integer, BrandCommand> methodMap = fillMap();
        int command = INTEGER_MINUS_ONE;

        while (command != INTEGER_ZERO) {
            LOGGER.info("Choose option: " + lineSeparator()
                + "0 - Exit" + lineSeparator()
                + "1 - Add brand" + lineSeparator()
                + "2 - Delete brand" + lineSeparator()
                + "3 - Get brand by name" + lineSeparator());

            try {
                command = ConsoleReader.readInt();
            } catch (IOException e) {
                LOGGER.error("Incorrect input. Try again!!!");
                continue;
            }

            methodMap.getOrDefault(command, new IncorrectInputBrandCommandImpl()).execute();
        }
    }

    private static Map<Integer, BrandCommand> fillMap() {
        int i = INTEGER_MINUS_ONE;
        Map<Integer, BrandCommand> commandMap = new HashMap<>();
        BrandService brandService = new BrandServiceImpl();
        commandMap.put(++i, new ExitBrandCommandImpl());
        commandMap.put(++i, new AddBrandCommandImpl(brandService));
        commandMap.put(++i, new DeleteBrandCommandImpl(brandService));
        commandMap.put(++i, new GetBrandCommandImpl(brandService));
        return commandMap;
    }
}
