package lab4.service;

import lab4.entity.Brand;

public interface BrandService {

    Brand add(Brand brand);

    String delete(String name);

    Brand get(String name);
}
