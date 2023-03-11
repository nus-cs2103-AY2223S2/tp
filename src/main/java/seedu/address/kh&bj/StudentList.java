package duke.assignment;

import java.util.ArrayList;

public class StudentList {
    private ArrayList<Student> students = new ArrayList<>();

    public StudentList() { // fake data
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i));
        }
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }
}
