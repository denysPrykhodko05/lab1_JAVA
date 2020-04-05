package lab3.command.impl;

import static java.lang.System.lineSeparator;
import static lab3.constants.ApplicationConstants.FILE_PATH;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import lab3.command.Command;
import lab3.entity.Group;
import lab3.entity.Student;
import lab3.util.ConsoleReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class CreateStudentCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(CreateStudentCommand.class);

    @Override
    public void execute() {
        Student student = fillStudent();
        Gson gson = new GsonBuilder().create();
        String studentJson = gson.toJson(student);

        try {
            FileWriter writer = new FileWriter(FILE_PATH, true);
            writer.write(studentJson + lineSeparator());
            writer.flush();
        } catch (IOException e) {
            LOGGER.info("Incorrect file path!!!");
        }
    }

    private boolean checkStudent(Student student) {
        return checkName(student.getName()) && checkGroup(student.getGroup()) && checkMark(student.getAverageMark());
    }

    private boolean checkName(String name) {
        return Objects.nonNull(name) && !StringUtils.EMPTY.equals(name);
    }

    private boolean checkGroup(Group group) {
        return !Objects.isNull(group);
    }

    private boolean checkMark(Double mark) {
        return mark > 0 && mark <= 100;
    }

    private Student fillStudent() {
        Student student = new Student();
        while (!checkStudent(student)) {
            try {

                if (!checkName(student.getName())) {
                    LOGGER.info("Enter name: ");
                    student.setName(ConsoleReader.readLine());
                }

                if (!checkGroup(student.getGroup())) {
                    LOGGER.info("Enter group: ");
                    String group = ConsoleReader.readLine().toLowerCase();

                    if (group.equalsIgnoreCase(Group.KIUKI_17_1.toString())
                        || group.equalsIgnoreCase(Group.KIUKI_17_2.toString())
                        || group.equalsIgnoreCase(Group.KIUKI_17_3.toString())) {
                        student.setGroup(Group.valueOf(group.toUpperCase()));
                    }
                }

                if (!checkMark(student.getAverageMark())) {
                    LOGGER.info("Enter average mark: ");
                    student.setAverageMark(ConsoleReader.readDouble());
                }

            } catch (IOException e) {
                LOGGER.info("Incorrect input. Try again!!!");
            }
        }
        return student;
    }
}
