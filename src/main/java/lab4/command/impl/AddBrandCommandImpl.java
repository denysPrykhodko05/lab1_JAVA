package lab4.command.impl;

import java.io.IOException;
import java.util.Objects;
import lab3.util.ConsoleReader;
import lab4.command.BrandCommand;
import lab4.entity.Brand;
import lab4.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class AddBrandCommandImpl implements BrandCommand{

    private static final Logger LOGGER = Logger.getLogger(AddBrandCommandImpl.class);
    private BrandService brandService;

    public AddBrandCommandImpl(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public void execute() {
        Brand brand = new Brand();
        String name = StringUtils.EMPTY;
        String manufacture = StringUtils.EMPTY;

        while (StringUtils.EMPTY.equals(name) || StringUtils.EMPTY.equals(manufacture)) {
            try {
                LOGGER.info("Enter name: ");
                name = ConsoleReader.readLine();
                LOGGER.info("Enter manufacture");
                manufacture = ConsoleReader.readLine();
            } catch (IOException e) {
                LOGGER.error("Incorrect input. Try again!!!");
                name = StringUtils.EMPTY;
                manufacture = StringUtils.EMPTY;
            }
        }

        brand.setName(name);
        brand.setManufacture(manufacture);

        if (Objects.isNull(brandService.add(brand))) {
            LOGGER.info("Cannot add. Try again!!!");
            return;
        }
        LOGGER.info("Brand" + brand.getName() + " was added");
    }
}
