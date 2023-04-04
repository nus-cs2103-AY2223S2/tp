package taa.model.assignment;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.transformation.FilteredList;
import taa.model.assignment.exceptions.*;
import taa.model.student.Student;

/**
 * List of assignments.
 * This is the class that the model uses to do all things related to assignments and submissions.
 */
public class AssignmentList {
    private final ArrayList<Assignment> assignments = new ArrayList<>();
    private final HashMap<String, Assignment> assignmentMap = new HashMap<>();

    /**
     * Adds an assignment to the AssignmentList.
     * @param assignmentName
     * @param sl
     * @param totalMarks
     * @throws DuplicateAssignmentException if an assignment with assignmentName already exists
     */
    public void add(String assignmentName, FilteredList<Student> sl, int totalMarks)
            throws DuplicateAssignmentException {
        if (assignmentMap.containsKey(assignmentName)) {
            throw new DuplicateAssignmentException(assignmentName);
        } else {
            Assignment a = new Assignment(assignmentName, sl, totalMarks);
            assignments.add(a);
            assignmentMap.put(assignmentName, a);
        }
    }

    /**
     * Deletes an assignment from the AssignmentList.
     * @param assignmentName
     * @throws AssignmentNotFoundException if an assignment with assignmentName does not exist in the AssignmentList
     */
    public void delete(String assignmentName) throws AssignmentNotFoundException {
        if (!assignmentMap.containsKey(assignmentName)) {
            throw new AssignmentNotFoundException(assignmentName);
        } else {
            Assignment removed = assignmentMap.remove(assignmentName);
            removed.delete();
            assignments.remove(removed);
        }
    }

    /**
     * Grades a student submission for an assignment of assignmentName.
     * The submission will be updated according to the marks given and whether it is a late submission.
     * @param assignmentName
     * @param student
     * @param marks
     * @param isLateSubmission
     * @throws AssignmentNotFoundException
     * @throws SubmissionNotFoundException
     * @throws InvalidGradeException
     */
    public void grade(String assignmentName, Student student, int marks, boolean isLateSubmission)
            throws AssignmentNotFoundException, SubmissionNotFoundException, InvalidGradeException {
        if (!assignmentMap.containsKey(assignmentName)) {
            throw new AssignmentNotFoundException(assignmentName);
        } else {
            assignmentMap.get(assignmentName).gradeSubmission(student, marks, isLateSubmission);
        }
    }

    /**
     * Resets the marks and late status of a student submission for an assignment of assignmentName
     * @param assignmentName
     * @param student
     * @throws AssignmentNotFoundException
     * @throws SubmissionNotFoundException
     */
    public void ungrade(String assignmentName, Student student) throws AssignmentNotFoundException,
            SubmissionNotFoundException {
        if (!assignmentMap.containsKey(assignmentName)) {
            throw new AssignmentNotFoundException(assignmentName);
        } else {
            assignmentMap.get(assignmentName).ungradeSubmission(student);
        }
    }

    /**
     * Lists out all the assignments and student submissions to the command line interface.
     * @return A string, which is the list of all assignments and submissions.
     */
    public String list() {
        StringBuilder sb = new StringBuilder();
        for (Assignment a : assignments) {
            sb.append("Assignment ").append(a).append(":\n");
            for (Submission s : a.getSubmissions()) {
                sb.append("  ").append(s).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * When a student is deleted, all the student's submissions must be deleted as well.
     * This deletes all of a student's submissions from the AssignmentList.
     * @param s the student
     */
    public void deleteStudent(Student s) {
        for (Assignment a : assignments) {
            a.deleteStudentSubmission(s);
        }
    }

    /**
     * When a student is added, all the existing assignments should be linked to the new student as well.
     * This creates new submissions for an added student for all the existing assignments.
     * @param s the student
     */
    public void addStudent(Student s) {
        for (Assignment a : assignments) {
            a.addStudent(s);
        }
    }

    /**
     * On startup, this will populate the assignment list and submissions from the
     * submission storage string data held by each student.
     * This is also called when we edit a student.
     * @param sl the student list
     */
    public void initFromStorage(FilteredList<Student> sl) throws CorruptAssignmentStorageException {
        if (sl.isEmpty()) {
            return;
        }
        // Step 0: Make sure everything empty.
        assignments.clear();
        assignmentMap.clear();

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
                if (toAdd == null) { // student has an assignment different from the first student.
                    assignments.clear();
                    assignmentMap.clear();
                    throw new CorruptAssignmentStorageException("Assignments must be the same for all students");
                }
                toAdd.addStudentSubmission(stu, submissionString);
            }
        }
    }

    /**
     * @param name
     * @return true if an assignment with the provided name exists.
     */
    public boolean contains(String name) {
        return this.assignmentMap.containsKey(name);
    }
}
