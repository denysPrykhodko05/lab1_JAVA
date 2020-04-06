package lab4.service;

import lab4.bean.ManufactureBean;

public interface ManufactureService {

    String add(String name);

    ManufactureBean get(String name);
}
