package taa.assignment;

import java.util.ArrayList;

import taa.model.student.Student;

/**
 * Student list
 */
public class StudentList {
    private ArrayList<Student> students = new ArrayList<>();

    /**
     * Creates a new StudentList instance.
     *
     * @param students An ArrayList of students to be added.
     */
    public StudentList(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }
}
