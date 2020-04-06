package lab4.service.manufactureserviceimpl;

import lab4.bean.ManufactureBean;
import lab4.dao.ManufactureDao;
import lab4.dao.manufacturedaoimpl.ManufactureDAOImpl;
import lab4.handler.TransactionHandler;
import lab4.manager.ConnectionManager;
import lab4.service.ManufactureService;

public class ManufactureServiceImpl implements ManufactureService {

    private final ManufactureDao manufactureDAO = new ManufactureDAOImpl();
    private TransactionHandler transactionHandler = new TransactionHandler(new ConnectionManager());

    @Override
    public String add(String name) {
        return transactionHandler.invokeTransaction(() -> manufactureDAO.add(name));
    }

    @Override
    public ManufactureBean get(String name) {
        return transactionHandler.invokeWithoutTransaction(()->manufactureDAO.get(name));
    }
}
