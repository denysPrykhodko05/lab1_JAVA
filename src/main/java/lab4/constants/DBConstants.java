package lab4.constants;

public class DBConstants {

    public static final String ADD_BRAND = "INSERT INTO brand (name, manufacture) VALUES (?, (SELECT id FROM manufacture WHERE name=?))";
    public static final String ADD_MANUFACTURE = "INSERT INTO manufacture (name) VALUES (?)";
    public static final String DELETE_BRAND = "DELETE FROM brand where brand.name = ?;";
    public static final String SELECT_BRAND_BY_NAME = "SELECT brand.id as id, brand.name as name, manufacture.name as manufacture FROM brand inner join manufacture on manufacture.id = brand.manufacture where brand.name = ?";
    public static final String SELECT_MANUFACTURE_BY_NAME = "SELECT * from manufacture where name = ?";
}
