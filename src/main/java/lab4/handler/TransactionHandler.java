package lab4.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Objects;
import lab4.daointerface.DAOInterface;
import lab4.manager.ConnectionManager;
import lab4.manager.TransactionManager;
import org.apache.log4j.Logger;

public class TransactionHandler {

    public static final String SAVEPOINT_INVOKE_TRANSACTION = "savepoint";
    private static final Logger LOGGER = Logger.getLogger(TransactionHandler.class);

    public TransactionHandler(ConnectionManager connectionManager) {
        ConnectionHolder.setConnection(connectionManager.getConnection());
    }

    public <T> T invokeTransaction(DAOInterface<T> method) {
        TransactionManager transactionManager = new TransactionManager();
        Connection connection = ConnectionHolder.getConnection();
        String savepointInvokeTransaction = SAVEPOINT_INVOKE_TRANSACTION;
        connection = checkConnection(connection);

        try {
            Savepoint savepoint = connection.setSavepoint(savepointInvokeTransaction);
            connection.setAutoCommit(false);
            T t = method.exec();
            connection.commit();
            return t;

        } catch (SQLException e) {
            transactionManager.rollback(connection);
        } finally {
            transactionManager.closeConnection(connection);
        }

        return null;
    }

    public <T> T invokeWithoutTransaction(DAOInterface<T> method) {
        TransactionManager transactionManager = new TransactionManager();
        Connection connection = ConnectionHolder.getConnection();
        checkConnection(connection);

        try {
            return method.exec();
        } catch (SQLException e) {
            LOGGER.error("Cannot execute method");
        } finally {
            transactionManager.closeConnection(connection);
        }

        return null;
    }

    private Connection checkConnection(Connection connection) {
        if (Objects.isNull(connection)) {
            ConnectionHolder.setConnection(new ConnectionManager().getConnection());
            return ConnectionHolder.getConnection();
        }
        return connection;
    }

}
