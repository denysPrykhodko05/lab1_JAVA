package lab4.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import org.apache.log4j.Logger;

public class ConnectionManager {

    public static final String dbUrl = "jdbc:mysql://localhost:3306/beer_shop?useSSL=false";
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);
    private Connection connection;
    private String login = "root";
    private String password = "root";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (Objects.isNull(connection)) {
                connection = DriverManager.getConnection(dbUrl, login, password);
            }
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Cannot get exception");
        }
        return null;
    }
}
