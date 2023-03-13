package taa.assignment;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.transformation.FilteredList;
import taa.model.student.Student;

/**
 * List of assignments
 */
public class AssignmentList {
    private ArrayList<Assignment> assignments = new ArrayList<>();
    private HashMap<String, Assignment> assignmentMap = new HashMap<>();

    /**
     * @param assignmentName
     * @param sl
     */
    public void add(String assignmentName, FilteredList<Student> sl) {
        if (assignmentMap.containsKey(assignmentName)) {
            System.out.println("Duplicate assignment name: " + assignmentName);
        } else {
            Assignment a = new Assignment(assignmentName, sl);
            assignments.add(a);
            assignmentMap.put(assignmentName, a);
        }
    }

    /**
     * @param assignmentName
     */
    public void delete(String assignmentName) {
        if (!assignmentMap.containsKey(assignmentName)) {
            System.out.println("Assignment: " + assignmentName + " not found");
        } else {
            assignments.remove(assignmentMap.remove(assignmentName));
        }
    }

    /**
     * @param assignmentName
     * @param studentId
     * @param marks
     */
    public void grade(String assignmentName, int studentId, int marks) {
        if (!assignmentMap.containsKey(assignmentName)) {
            System.out.println("Assignment: " + assignmentName + " not found");
        } else {
            assignmentMap.get(assignmentName).gradeSubmission(studentId, marks);
        }
    }
    // TODO: delete student from classList = must delete all submissions of studentId for all assignments.

}
