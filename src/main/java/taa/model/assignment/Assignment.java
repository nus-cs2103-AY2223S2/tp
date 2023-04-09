package taa.model.assignment;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.transformation.FilteredList;
import taa.model.assignment.exceptions.InvalidGradeException;
import taa.model.assignment.exceptions.SubmissionNotFoundException;
import taa.model.student.Name;
import taa.model.student.Student;

/**
 * Assignment class, contains a list of student submissions.
 */
public class Assignment {
    private final String name;
    private final int totalMarks;
    private final ArrayList<Submission> submissions = new ArrayList<>();
    private final HashMap<Student, Submission> submissionMap = new HashMap<>();

    /**
     * Creates an assignment with specified totalMarks.
     * This is used when creating from storage.
     * @param name
     * @param totalMarks
     */
    public Assignment(String name, int totalMarks) {
        this.name = name;
        this.totalMarks = totalMarks;
    }

    /**
     * Creates an assignment with specified totalMarks,
     * and then for every student in the student list: creates a new submission for them.
     * @param name name of the assignment
     * @param sl   the student list.
     * @param totalMarks
     */
    public Assignment(String name, FilteredList<Student> sl, int totalMarks) {
        this.name = name;
        this.totalMarks = totalMarks;
        for (Student stu : sl) {
            addStudent(stu);
        }
    }

    public int getTotalMarks() {
        return this.totalMarks;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Submission> getSubmissions() {
        return this.submissions;
    }

    /**
     * Adds a new assignment to a student.
     * @param stu
     */
    public void addStudent(Student stu) {
        Submission sub = new Submission(stu, this);
        submissions.add(sub);
        stu.addSubmission(sub);
        submissionMap.put(stu, sub);
    }

    /**
     * Adds a student submission using the submission string from storage.
     * @param stu
     * @param submissionString
     */
    public void addStudentSubmission(Student stu, String submissionString) {
        String[] words = submissionString.split(Submission.STR_SEP);
        boolean isGraded = Integer.parseInt(words[1]) == 1;
        boolean isLateSubmission = Integer.parseInt(words[2]) == 1;
        Submission sub = new Submission(stu, this, isGraded, isLateSubmission, Integer.parseInt(words[3]));
        submissions.add(sub);
        submissionMap.put(stu, sub);
        stu.addSubmission(sub);
    }

    /**
     * Grades a student submission of an assignment.
     * @param student
     * @param marks
     * @param isLateSubmission
     * @throws SubmissionNotFoundException if there is no submission for that student.
     * @throws InvalidGradeException if given marks < 0 or > totalMarks.
     */
    public void gradeSubmission(Student student, int marks, boolean isLateSubmission)
            throws SubmissionNotFoundException, InvalidGradeException {
        if (submissionMap.containsKey(student)) {
            submissionMap.get(student).grade(marks, isLateSubmission);
        } else {
            throw new SubmissionNotFoundException(student.getName().fullName);
        }
    }

    /**
     * Resets the marks and late status of a student submission.
     * @param student
     * @throws SubmissionNotFoundException if there is no submission for that student.
     */
    public void ungradeSubmission(Student student) throws SubmissionNotFoundException {
        if (submissionMap.containsKey(student)) {
            submissionMap.get(student).ungrade();
        } else {
            throw new SubmissionNotFoundException(student.getName().fullName);
        }
    }

    /**
     * Ties up loose end to prepare this Assignment for deletion.
     */
    public void delete() {
        submissionMap.forEach(Student::deleteSubmission);
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Deletes a student submission from the submission list.
     * @param s the student
     */
    public void deleteStudentSubmission(Student s) {
        Submission toDelete = null;
        for (Submission sub : submissions) {
            if (sub.getStudent().equals(s)) {
                toDelete = sub; // this is ok as a student can only have at most 1 submission per assignment.
            }
        }
        if (toDelete != null) { // if student doesn't have submission for that assignment.
            submissions.remove(toDelete);
        }
    }

    /**
     * Checks whether an assignment name is valid.
     * @param name
     * @return
     */
    public static boolean isValidAssignmentName(String name) {
        return Name.isValidName(name);
    }

    /**
     * Checks whether assignment marks are valid.
     * @param marks
     * @return
     */
    public static boolean isValidAssignmentMarks(int marks) {
        return marks >= 0;
    }
}
