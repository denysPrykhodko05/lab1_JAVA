package lab3.command.impl;

import static lab3.constants.ApplicationConstants.FILE_PATH;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lab3.command.Command;
import lab3.entity.Student;
import org.apache.log4j.Logger;

public class ReadStudentCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ReadStudentCommand.class);

    @Override
    public void execute() {
        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);
            List<Student> studentList = new ArrayList<>();
            Gson gson = new Gson();

            while (scanner.hasNextLine()) {
                String studentJson = scanner.nextLine();
                Student student = gson.fromJson(studentJson, Student.class);
                studentList.add(student);
            }

            studentList.stream().filter(s->s.getAverageMark() >= 75).forEach(LOGGER::info);

        } catch (FileNotFoundException e) {
            LOGGER.info("File not found.");
        }
    }
}
