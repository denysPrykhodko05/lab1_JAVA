package lab4.dao;

import lab4.bean.ManufactureBean;

public interface ManufactureDao {

    String add(String name);

    ManufactureBean get(String name);
}
