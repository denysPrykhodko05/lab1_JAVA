package lab4.command.impl;

import java.util.Objects;
import lab4.command.BrandCommand;
import lab4.service.BrandService;
import org.apache.log4j.Logger;

public class DeleteBrandCommandImpl implements BrandCommand {

    private static final Logger LOGGER = Logger.getLogger(DeleteBrandCommandImpl.class);
    private BrandService brandService;

    public DeleteBrandCommandImpl(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public void execute() {
        String name = fillName();

        if(Objects.isNull(brandService.delete(name))){
            LOGGER.info("Cannot delete brand");
            return;
        }

        LOGGER.info("Brand " + name + " was removed.");
    }
}
