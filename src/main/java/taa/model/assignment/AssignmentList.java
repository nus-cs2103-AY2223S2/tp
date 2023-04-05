package taa.model.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javafx.collections.transformation.FilteredList;
import taa.logic.parser.ParserUtil;
import taa.logic.parser.exceptions.ParseException;
import taa.model.assignment.exceptions.AssignmentNotFoundException;
import taa.model.assignment.exceptions.DuplicateAssignmentException;
import taa.model.assignment.exceptions.InvalidGradeException;
import taa.model.assignment.exceptions.SubmissionNotFoundException;
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
     * Checks whether all the submission storage strings in the json file are valid.
     * @param sl
     * @throws ParseException
     */
    public void checkValidStorage(FilteredList<Student> sl) throws ParseException {
        HashMap<String, Integer> assignmentCount = new HashMap<>();
        // Step 1. Gets all submission strings, check whether they are length 5 and contains the correct input format.
        // Step 2. adds it to the assignmentCount.
        for (Student stu : sl) {
            HashSet<String> studentAssignment = new HashSet<>(); // checks if a student has the same assignment.
            for (String sub : stu.getSubmissionStorageStrings()) {
                String[] words = sub.split(",");
                if (words.length != 5) {
                    throw new ParseException("Submission storage string does not have 4 commas");
                }
                // Try to parse the input to make sure they are valid.
                String assignmentName = ParserUtil.parseName(words[0]).toString();
                int isGraded = ParserUtil.parseInt(words[1]);
                int isLate = ParserUtil.parseInt(words[2]);
                int totalMarks = ParserUtil.parseInt(words[4]);
                if (!(isGraded == 0 || isGraded == 1) || !(isLate == 0 || isLate == 1) || totalMarks < 0) {
                    throw new ParseException("Invalid range for isGraded, isLate, totalMarks in storage string");
                }
                // Increase the count of the assignment by 1.
                assignmentCount.put(assignmentName, assignmentCount.getOrDefault(assignmentName, 0) + 1);

                // Checks for duplicate assignment for a single student.
                if (studentAssignment.contains(assignmentName)) {
                    throw new ParseException("Duplicate assignment for a student found");
                }
                studentAssignment.add(assignmentName);
            }
        }

        // Step 3. Checks for whether all students have that assignment.
        for (int v : assignmentCount.values()) {
            if (v != sl.size()) {
                throw new ParseException("An assignment is not shared by all students.");
            }
        }
    }

    /**
     * On startup, this will populate the assignment list and submissions from the
     * submission storage string data held by each student.
     * This is also called when we edit a student.
     * @param sl the student list
     */
    public void initFromStorage(FilteredList<Student> sl) {
        if (sl.isEmpty()) {
            return;
        }
        try {
            checkValidStorage(sl);
        } catch (ParseException e) {
            System.out.println("Parsing of submission storage string error: " + e.getMessage());
            // Calling logger here seems sus, what is a better design? also is this the best design to do the checking?
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
