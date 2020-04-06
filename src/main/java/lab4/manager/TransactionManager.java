package lab4.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import lab4.handler.ConnectionHolder;
import org.apache.log4j.Logger;

public class TransactionManager {

    private static final Logger LOGGER = Logger.getLogger(TransactionManager.class);

    public void closeConnection(Connection connection) {
        if (Objects.nonNull(connection)) {

            try {
                connection.close();
                ConnectionHolder.removeConnection();
            } catch (SQLException e) {
            }

        }
    }

    public void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
        }
    }

}

