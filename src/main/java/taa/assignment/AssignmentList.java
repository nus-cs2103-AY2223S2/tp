package taa.assignment;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.transformation.FilteredList;
import taa.logic.commands.exceptions.CommandException;
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
    public void add(String assignmentName, FilteredList<Student> sl) throws CommandException {
        if (assignmentMap.containsKey(assignmentName)) {
            throw new CommandException("Duplicate assignment name: " + assignmentName);
        } else {
            Assignment a = new Assignment(assignmentName, sl);
            assignments.add(a);
            assignmentMap.put(assignmentName, a);
        }
    }

    /**
     * @param assignmentName
     */
    public void delete(String assignmentName) throws CommandException {
        if (!assignmentMap.containsKey(assignmentName)) {
            throw new CommandException("Assignment: " + assignmentName + " not found");
        } else {
            assignments.remove(assignmentMap.remove(assignmentName));
        }
    }

    /**
     * @param assignmentName
     * @param studentId
     * @param marks
     */
    public void grade(String assignmentName, int studentId, int marks) throws CommandException {
        if (!assignmentMap.containsKey(assignmentName)) {
            throw new CommandException("Assignment: " + assignmentName + " not found");
        } else {
            assignmentMap.get(assignmentName).gradeSubmission(studentId, marks);
        }
    }
    // TODO: delete student from classList = must delete all submissions of studentId for all assignments.

    public String list() {
        StringBuilder sb = new StringBuilder();
        for (Assignment a : assignments) {
            sb.append("Assignment " +  a + ":\n");
            for (Submission s : a.getSubmissions()) {
                sb.append("  " + s + "\n");
            }
        }
        return sb.toString();
    }
}
