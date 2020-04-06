package lab4.service.brandserviceimpl;

import java.util.Objects;
import lab4.bean.ManufactureBean;
import lab4.dao.BrandDao;
import lab4.dao.branddaoimpl.BrandDAOImpl;
import lab4.entity.Brand;
import lab4.handler.TransactionHandler;
import lab4.manager.ConnectionManager;
import lab4.service.BrandService;
import lab4.service.ManufactureService;
import lab4.service.manufactureserviceimpl.ManufactureServiceImpl;

public class BrandServiceImpl implements BrandService {

    private final BrandDao brandDao = new BrandDAOImpl();
    private final ManufactureService manufactureService = new ManufactureServiceImpl();
    private TransactionHandler transactionHandler = new TransactionHandler(new ConnectionManager());

    @Override
    public Brand add(Brand brand) {
        ManufactureBean manufactureBean = transactionHandler.invokeWithoutTransaction(() -> manufactureService.get(brand.getManufacture()));

        if (Objects.isNull(manufactureBean)) {
            transactionHandler.invokeTransaction(() -> manufactureService.add(brand.getManufacture()));
        }

        return transactionHandler.invokeTransaction(() -> brandDao.add(brand));
    }

    @Override
    public String delete(String name) {
        return transactionHandler.invokeTransaction(() -> brandDao.delete(name));
    }

    @Override
    public Brand get(String name) {
        return transactionHandler.invokeWithoutTransaction(() -> brandDao.get(name));
    }
}
