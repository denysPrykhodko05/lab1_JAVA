package lab4.dao.branddaoimpl;

import static lab4.constants.DBConstants.ADD_BRAND;
import static lab4.constants.DBConstants.DELETE_BRAND;
import static lab4.constants.DBConstants.SELECT_BRAND_BY_NAME;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lab4.dao.BrandDao;
import lab4.entity.Brand;
import lab4.handler.ConnectionHolder;
import org.apache.log4j.Logger;

public class BrandDAOImpl implements BrandDao {

    private static final Logger LOGGER = Logger.getLogger(BrandDAOImpl.class);

    @Override
    public Brand add(Brand brand) {
        try (PreparedStatement preparedStatement = ConnectionHolder.getConnection().prepareStatement(ADD_BRAND)) {
            fillPrepareStatementByBrand(preparedStatement, brand);
            if (preparedStatement.executeUpdate() > INTEGER_ZERO) {
                return brand;
            }
            return null;
        } catch (SQLException e) {
            LOGGER.error("Cannot add brand");
        }
        return brand;
    }

    @Override
    public String delete(String name) {
        try (PreparedStatement pstm = ConnectionHolder.getConnection().prepareStatement(DELETE_BRAND)) {
            pstm.setString(1, name);

            if (pstm.executeUpdate() > INTEGER_ZERO) {
                return name;
            }

        } catch (SQLException e) {
            LOGGER.error("Cannot delete brand");
        }

        return null;
    }

    @Override
    public Brand get(String name) {
        try (PreparedStatement pstm = ConnectionHolder.getConnection().prepareStatement(SELECT_BRAND_BY_NAME)) {
            pstm.setString(1, name);
            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                return parseResultSetForBrand(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get brand");
        }

        return null;
    }

    private void fillPrepareStatementByBrand(PreparedStatement pstm, Brand brand) throws SQLException {
        int i = 0;
        pstm.setString(++i, brand.getName());
        pstm.setString(++i, brand.getManufacture());
    }

    private Brand parseResultSetForBrand(ResultSet resultSet) throws SQLException {
        Brand brand = new Brand();
        brand.setId(resultSet.getInt(1));
        brand.setName(resultSet.getString(2));
        brand.setManufacture(resultSet.getString(3));
        return brand;
    }
}
