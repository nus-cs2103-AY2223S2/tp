package taa.assignment;

import java.util.Optional;

import taa.assignment.exceptions.InvalidGradeException;
import taa.model.student.Student;

/**
 * An assignment submission
 */

public class Submission {
    private boolean isGraded = false;
    private boolean isLateSubmission = false;
    private int marks = 0;
    private final Assignment assignment;
    private final Student student;

    /**
     * @param student The student who made this submission.
     */
    public Submission(Student student, Assignment assignment) {
        this.student = student;
        this.assignment = assignment;
    }

    /**
     * Creates a Submission, used when creating from storage.
     * @param student
     * @param assignment
     * @param isGraded
     * @param isLateSubmission
     * @param marks
     */
    public Submission(Student student, Assignment assignment, boolean isGraded,
                      boolean isLateSubmission, int marks) {
        this.student = student;
        this.assignment = assignment;
        this.isGraded = isGraded;
        this.isLateSubmission = isLateSubmission;
        this.marks = marks;
    }

    public Student getStudent() {
        return this.student;
    }

    /**
     * Grades a students submission.
     * @param marks
     * @throws InvalidGradeException when marks is out of range 0-totalMarks.
     */
    public void grade(int marks, boolean isLateSubmission) throws InvalidGradeException {
        if (marks < 0) {
            throw new InvalidGradeException("Student marks is less than 0");
        }
        int totalMarks = this.assignment.getTotalMarks();
        if (marks > totalMarks) {
            throw new InvalidGradeException("Student marks is more than assignment total marks of " + totalMarks);
        }
        isGraded = true;
        this.marks = marks;
        this.isLateSubmission = isLateSubmission;
    }

    /**
     * Resets the grade of a student submission.
     */
    public void ungrade() {
        this.isGraded = false;
        this.isLateSubmission = false;
        this.marks = 0;
    }

    /**
     * Describes the assignment this submission belongs to, along with details of this submission.
     */
    public String describeSubmission() {
        String gradeStatus = this.isGraded ? marks + "/" + assignment.getTotalMarks() : "Ungraded";
        String late = this.isLateSubmission ? "(*Late Submission*)" : "";
        return String.format("%s (Grade: %s) %s", this.assignment.toString(), gradeStatus, late);
    }

    /**
     * Returns true if this submission belongs to an assignment with the given name.
     */
    public boolean isForAssignment(String assignmentName) {
        return this.assignment.toString().equals(assignmentName);
    }

    /**
     * Returns the score of this submission, if graded.
     * Otherwise, an Optional.empty() is returned.
     */
    public Optional<Integer> getScore() {
        if (isGraded) {
            return Optional.of(this.marks);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        char gradeChar = isGraded ? 'X' : ' ';
        String late = this.isLateSubmission ? "(*Late Submission*)" : "";
        return String.format("[%c] %s: %d/%d marks. %s", gradeChar, student.getName().fullName,
                marks, assignment.getTotalMarks(), late);
    }

    /**
     * Creates a string for a submission to store into our storage.
     * @return The storage string
     */
    public String toStorageString() {
        String assignmentName = assignment.getName();
        int graded = isGraded ? 1 : 0;
        int late = isLateSubmission ? 1 : 0;
        return String.format("%s,%d,%d,%d,%d", assignmentName, graded, late, marks, assignment.getTotalMarks());
    }

}
