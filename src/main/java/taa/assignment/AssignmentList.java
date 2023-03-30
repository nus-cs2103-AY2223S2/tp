package taa.assignment;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.transformation.FilteredList;
import taa.assignment.exceptions.AssignmentNotFoundException;
import taa.assignment.exceptions.InvalidGradeException;
import taa.assignment.exceptions.SubmissiontNotFoundException;
import taa.model.student.Student;

/**
 * List of assignments
 */
public class AssignmentList {
    private final ArrayList<Assignment> assignments = new ArrayList<>();
    private final HashMap<String, Assignment> assignmentMap = new HashMap<>();

    /**
     * @param assignmentName
     * @param sl
     */
    public void add(String assignmentName, FilteredList<Student> sl, int totalMarks)
            throws AssignmentNotFoundException {
        if (assignmentMap.containsKey(assignmentName)) {
            throw new AssignmentNotFoundException("Duplicate assignment name: " + assignmentName);
        } else {
            Assignment a = new Assignment(assignmentName, sl, totalMarks);
            assignments.add(a);
            assignmentMap.put(assignmentName, a);
        }
    }

    /**
     * @param assignmentName
     */
    public void delete(String assignmentName) throws AssignmentNotFoundException {
        if (!assignmentMap.containsKey(assignmentName)) {
            throw new AssignmentNotFoundException("Assignment: " + assignmentName + " not found");
        } else {
            Assignment removed = assignmentMap.remove(assignmentName);
            removed.delete();
            assignments.remove(removed);
        }
    }

    /**
     * @param assignmentName
     * @param student
     * @param marks
     * @param isLateSubmission
     */
    public void grade(String assignmentName, Student student, int marks, boolean isLateSubmission)
            throws AssignmentNotFoundException, SubmissiontNotFoundException, InvalidGradeException {
        if (!assignmentMap.containsKey(assignmentName)) {
            throw new AssignmentNotFoundException("Assignment: " + assignmentName + " not found");
        } else {
            assignmentMap.get(assignmentName).gradeSubmission(student, marks, isLateSubmission);
        }
    }

    /**
     * @param assignmentName
     * @param student
     * @throws AssignmentNotFoundException
     * @throws SubmissiontNotFoundException
     */
    public void ungrade(String assignmentName, Student student) throws AssignmentNotFoundException,
            SubmissiontNotFoundException {
        if (!assignmentMap.containsKey(assignmentName)) {
            throw new AssignmentNotFoundException("Assignment: " + assignmentName + " not found");
        } else {
            assignmentMap.get(assignmentName).ungradeSubmission(student);
        }
    }

    /**
     * @return A list of all assignments and submissions
     */
    public String list() {
        StringBuilder sb = new StringBuilder();
        for (Assignment a : assignments) {
            sb.append("Assignment " + a + ":\n");
            for (Submission s : a.getSubmissions()) {
                sb.append("  " + s + "\n");
            }
        }
        return sb.toString();
    }

    /**
     * Delete all of a student's submissions from the assignment list.
     * @param s the student
     */
    public void deleteStudent(Student s) {
        for (Assignment a : assignments) {
            a.deleteStudentSubmission(s);
        }
    }

    /**
     * Creates new submissions for an added student.
     * @param s the student
     */
    public void addStudent(Student s) {
        for (Assignment a : assignments) {
            a.addStudent(s);
        }
    }

    /**
     * Populates the assignment list, submissions list into
     * @param sl the student list
     */
    public void initFromStorage(FilteredList<Student> sl) {
        if (sl.isEmpty()) {
            return;
        }
        // Step 1: populate the assignment list and hashmap with empty assignments.
        Student firstStudent = sl.get(0);
        for (String submissionString : firstStudent.getSubmissionStorageStrings()) {
            String[] words = submissionString.split(",");
            Assignment a = new Assignment(words[0], Integer.parseInt(words[4]));
            assignments.add(a);
            assignmentMap.put(words[0], a);
        }

        // Step 2: populate each assignment with each student submission.
        for (Student stu : sl) {
            for (String submissionString : stu.getSubmissionStorageStrings()) {
                String assignmentName = submissionString.split(",")[0];
                Assignment toAdd = assignmentMap.get(assignmentName);
                toAdd.addStudentSubmission(stu, submissionString);
            }
        }
    }
}
