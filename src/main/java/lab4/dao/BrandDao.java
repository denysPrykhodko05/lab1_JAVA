package lab4.dao;

import lab4.entity.Brand;

public interface BrandDao {

    Brand add(Brand brand);

    String delete(String name);

    Brand get(String name);
}
