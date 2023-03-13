package taa.assignment;

import java.util.ArrayList;
import java.util.HashMap;

import taa.model.student.Student;


/**
 * Assignment class.
 */
public class Assignment {
    private String name;
    private ArrayList<Submission> submissions = new ArrayList<>();
    private final HashMap<Integer, Submission> submissionMap = new HashMap<>();


    /**
     * @param name name of the assignment
     * @param sl   the list of students from classlist.
     */
    public Assignment(String name, StudentList sl) {
        this.name = name;
        for (Student stu : sl.getStudents()) {
            Submission sub = new Submission(stu.getId());
            submissions.add(sub);
            submissionMap.put(stu.getId(), sub);
        }
    }

    /**
     * Grades a student submission of an assignment.
     *
     * @param studentId
     * @param marks
     */
    public void gradeSubmission(int studentId, int marks) {
        if (submissionMap.containsKey(studentId)) {
            submissionMap.get(studentId).grade(marks);
        } else {
            System.out.println("Submission of " + studentId + "not found");
        }
    }
}
