package lab4.command.impl;

import java.util.Objects;
import lab4.command.BrandCommand;
import lab4.entity.Brand;
import lab4.service.BrandService;
import org.apache.log4j.Logger;

public class GetBrandCommandImpl implements BrandCommand {

    private static final Logger LOGGER = Logger.getLogger(GetBrandCommandImpl.class);
    private BrandService brandService;

    public GetBrandCommandImpl(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public void execute() {
        String name = fillName();
        Brand brand = brandService.get(name);

        if (Objects.isNull(brand)) {
            LOGGER.info("Cannot get brand " + name);
        }

        LOGGER.info(brand);
    }
}
