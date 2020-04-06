package lab4.dao.manufacturedaoimpl;

import static lab4.constants.DBConstants.ADD_MANUFACTURE;
import static lab4.constants.DBConstants.SELECT_MANUFACTURE_BY_NAME;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lab4.bean.ManufactureBean;
import lab4.dao.ManufactureDao;
import lab4.handler.ConnectionHolder;
import org.apache.log4j.Logger;

public class ManufactureDAOImpl implements ManufactureDao {

    private static final Logger LOGGER = Logger.getLogger(ManufactureDAOImpl.class);

    @Override
    public String add(String name) {
        try (PreparedStatement preparedStatement = ConnectionHolder.getConnection().prepareStatement(ADD_MANUFACTURE)) {
            preparedStatement.setString(1, name);

            if (preparedStatement.executeUpdate() > INTEGER_ZERO) {
                return name;
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot add manufacture");
        }

        return null;
    }

    @Override
    public ManufactureBean get(String name) {
        try (PreparedStatement pstm = ConnectionHolder.getConnection().prepareStatement(SELECT_MANUFACTURE_BY_NAME)) {
            pstm.setString(1, name);
            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                return parseResultSetToManufactureBean(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get manufacture");
        }
        return null;
    }

    private ManufactureBean parseResultSetToManufactureBean(ResultSet resultSet) throws SQLException {
        ManufactureBean manufactureBean = new ManufactureBean();
        int i = 0;
        manufactureBean.setId(resultSet.getInt(++i));
        manufactureBean.setName(resultSet.getString(++i));
        return manufactureBean;
    }
}
