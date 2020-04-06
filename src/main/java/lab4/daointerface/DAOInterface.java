package lab4.daointerface;

import java.sql.SQLException;

public interface DAOInterface<T> {

    T exec() throws SQLException;
}
