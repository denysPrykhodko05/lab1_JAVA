package lab4.entity;

import static java.lang.System.lineSeparator;

public class Brand {

    private int id;
    private String name;
    private String manufacture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    @Override
    public String toString() {
        return "Id: " + id + lineSeparator()
            + "Name: " + name + lineSeparator()
            + "Manufacture: " + manufacture + lineSeparator();
    }
}
