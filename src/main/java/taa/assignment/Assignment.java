package taa.assignment;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.transformation.FilteredList;
import taa.assignment.exceptions.InvalidGradeException;
import taa.assignment.exceptions.SubmissiontNotFoundException;
import taa.model.student.Student;

/**
 * Assignment class.
 */
public class Assignment {
    private final String name;
    private final int totalMarks = 100;
    private final ArrayList<Submission> submissions = new ArrayList<>();
    private final HashMap<Student, Submission> submissionMap = new HashMap<>();


    /**
     * @param name name of the assignment
     * @param sl   the list of students from classlist.
     */
    public Assignment(String name, FilteredList<Student> sl) {
        this.name = name;
        for (Student stu : sl) {
            Submission sub = new Submission(stu, this);
            submissions.add(sub);
            stu.addSubmission(sub);
            submissionMap.put(stu, sub);
        }
    }

    /**
     * Grades a student submission of an assignment.
     *
     * @param student
     * @param marks
     * @param isLateSubmission
     */
    public void gradeSubmission(Student student, int marks, boolean isLateSubmission) throws SubmissiontNotFoundException, InvalidGradeException {
        if (submissionMap.containsKey(student)) {
            submissionMap.get(student).grade(marks, isLateSubmission);
        } else {
            throw new SubmissiontNotFoundException("Submission of " + student.getName().fullName + " not found");
        }
    }

    /**
     * @param student
     * @throws SubmissiontNotFoundException
     */
    public void ungradeSubmission(Student student) throws SubmissiontNotFoundException {
        if (submissionMap.containsKey(student)) {
            submissionMap.get(student).ungrade();
        } else {
            throw new SubmissiontNotFoundException("Submission of " + student.getName().fullName + " not found");
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
        submissionMap.forEach((student, submission) -> {
            student.deleteSubmission(submission);
        });
    }

    @Override
    public String toString() {
        return this.name;
    }
}
