package lab3.entity;

public class Student {

    private String name;
    private Group group;
    private double averageMark;

    public Student() {

    }

    public Student(String name, Group group, double averageMark) {
        this.name = name;
        this.group = group;
        this.averageMark = averageMark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    @Override
    public String toString() {
        return "Name: " + name
            + " Group: " + group
            + " Mark: " + averageMark;
    }
}
