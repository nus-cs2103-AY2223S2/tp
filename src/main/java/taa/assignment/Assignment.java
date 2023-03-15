package taa.assignment;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.transformation.FilteredList;
import taa.logic.commands.exceptions.CommandException;
import taa.model.student.Student;

/**
 * Assignment class.
 */
public class Assignment {
    private String name;
    private ArrayList<Submission> submissions = new ArrayList<>();
    private final HashMap<Student, Submission> submissionMap = new HashMap<>();


    /**
     * @param name name of the assignment
     * @param sl   the list of students from classlist.
     */
    public Assignment(String name, FilteredList<Student> sl) {
        this.name = name;
        for (Student stu : sl) {
            Submission sub = new Submission(stu);
            submissions.add(sub);
            submissionMap.put(stu, sub);
        }
    }

    /**
     * Grades a student submission of an assignment.
     *
     * @param studentId
     * @param marks
     */
    public void gradeSubmission(int studentId, int marks) throws CommandException {
        if (submissionMap.containsKey(studentId)) {
            submissionMap.get(studentId).grade(marks);
        } else {
            throw new CommandException("Submission of " + studentId + "not found");
        }
    }

    public ArrayList<Submission> getSubmissions() {
        return this.submissions;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
