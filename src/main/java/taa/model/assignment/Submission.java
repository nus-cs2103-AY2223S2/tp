package taa.model.assignment;

import java.util.Optional;

import taa.model.assignment.exceptions.InvalidGradeException;
import taa.model.student.Student;

/**
 * An assignment submission for a particular student.
 */
public class Submission {
    public static final String STR_SEP = ",";
    private final Assignment assignment;
    private final Student student;
    private boolean isGraded = false;
    private boolean isLateSubmission = false;
    private int marks = 0;

    /**
     * Constructor for our submission.
     *
     * @param student
     * @param assignment
     */
    public Submission(Student student, Assignment assignment) {
        this.student = student;
        this.assignment = assignment;
    }

    /**
     * Creates a Submission, this is used when creating from storage.
     *
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
     * Grades a students submission with the given marks.
     *
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
        student.updateSubmission(this);
    }

    /**
     * Resets the marks, and late status of a student submission.
     */
    public void ungrade() {
        this.isGraded = false;
        this.isLateSubmission = false;
        this.marks = 0;
        student.updateSubmission(this);
    }

    /**
     * Describes the assignment this submission belongs to, along with details of this submission.
     *
     * @return the details of the submission
     */
    public String describeSubmission() {
        String gradeStatus = this.isGraded ? marks + "/" + assignment.getTotalMarks() : "Ungraded";
        String late = this.isLateSubmission ? "(*Late Submission*)" : "";
        return String.format("%s (Grade: %s) %s", this.assignment.toString(), gradeStatus, late);
    }

    /**
     * @param assignmentName
     * @return true if this submission belongs to an assignment with the given name.
     */
    public boolean isForAssignment(String assignmentName) {
        return this.assignment.toString().equals(assignmentName);
    }

    /**
     * @return Returns the score of this submission, if graded. Otherwise, an Optional.empty() is returned.
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
        return String.format("[%c] %s: %d/%d marks. %s", gradeChar, student.getName(),
                marks, assignment.getTotalMarks(), late);
    }

    /**
     * Creates a string for a submission to store into our storage. The data given is separated by commas, in the
     * following format: "assignmentName,isGraded,isLate,marks"
     *
     * @return The storage string
     */
    public String toStorageString() {
        final String assignmentName = assignment.getName();
        final int graded = isGraded ? 1 : 0;
        final int late = isLateSubmission ? 1 : 0;
        return assignmentName + STR_SEP + graded + STR_SEP + late + STR_SEP + marks;
    }
}
