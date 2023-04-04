package taa.assignment;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.transformation.FilteredList;
import taa.assignment.exceptions.InvalidGradeException;
import taa.assignment.exceptions.SubmissionNotFoundException;
import taa.model.student.Student;

/**
 * Assignment class.
 */
public class Assignment {
    private final String name;
    private final int totalMarks;
    private final ArrayList<Submission> submissions = new ArrayList<>();
    private final HashMap<Student, Submission> submissionMap = new HashMap<>();


    /**
     * Called using add_asgn
     * @param name name of the assignment
     * @param sl   the list of students from classlist.
     */
    public Assignment(String name, FilteredList<Student> sl, int totalMarks) {
        this.name = name;
        this.totalMarks = totalMarks;
        for (Student stu : sl) {
            addStudent(stu);
        }
    }

    /**
     * Called when in storage
     * @param name
     * @param totalMarks
     */
    public Assignment(String name, int totalMarks) {
        this.name = name;
        this.totalMarks = totalMarks;
    }

    /**
     * Adds a new assignment to a student
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
        String[] words = submissionString.split(",");
        boolean isGraded = Integer.parseInt(words[1]) == 1;
        boolean isLateSubmission = Integer.parseInt(words[2]) == 1;
        Submission sub = new Submission(stu, this, isGraded, isLateSubmission, Integer.parseInt(words[3]));
        submissions.add(sub);
        submissionMap.put(stu, sub);
        stu.addSubmission(sub);
    }

    /**
     * Grades a student submission of an assignment.
     *
     * @param student
     * @param marks
     * @param isLateSubmission
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
     * @param student
     * @throws SubmissionNotFoundException
     */
    public void ungradeSubmission(Student student) throws SubmissionNotFoundException {
        if (submissionMap.containsKey(student)) {
            submissionMap.get(student).ungrade();
        } else {
            throw new SubmissionNotFoundException(student.getName().fullName);
        }
    }


    public ArrayList<Submission> getSubmissions() {
        return this.submissions;
    }

    public int getTotalMarks() {
        return this.totalMarks;
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
     * Deletes a student submission from the submission list
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

    public String getName() {
        return this.name;
    }

}
