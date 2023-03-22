package taa.assignment;

import java.util.Date;

import taa.assignment.exceptions.InvalidGradeException;
import taa.model.student.Student;

/**
 * An assignment submission
 */
public class Submission implements Comparable<Submission> {
    private boolean isGraded = false;
    private boolean isLateSubmission = false;
    private int marks = 0;
    private Assignment assignment;
    private Student student;
    private Date timeCreated;


    /**
     * @param student The student who made this submission.
     */
    public Submission(Student student, Assignment assignment) {
        this.student = student;
        this.assignment = assignment;
        this.timeCreated = new Date();
    }

    /**
     * Grades a students submission.
     * @param marks
     * @throws InvalidGradeException when marks is out of range 0-totalMarks.
     */
    public void grade(int marks) throws InvalidGradeException {
        if (marks < 0) {
            throw new InvalidGradeException("Student marks is less than 0");
        }
        int totalMarks = this.assignment.getTotalMarks();
        if (marks > totalMarks) {
            throw new InvalidGradeException("Student marks is more than assignment total marks of " + totalMarks);
        }
        isGraded = true;
        this.marks = marks;
        this.timeCreated = new Date();
    }

    /**
     * Grades a late submission.
     * @param marks
     * @throws InvalidGradeException
     */
    public void gradeLateSubmission(int marks) throws InvalidGradeException {
        grade(marks);
        this.isLateSubmission = true;
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
        String gradeStatus = this.isGraded ? Integer.toString(marks) : "Ungraded";
        return String.format("%s (Grade: %s)", this.assignment.toString(), gradeStatus);
    }

    @Override
    public String toString() {
        char gradeChar = isGraded ? 'X' : ' ';
        return String.format("[%c] %s: %d marks.", gradeChar, student.getName().fullName, marks);
    }

    @Override
    public int compareTo(Submission otherSubmission) {
        return this.timeCreated.compareTo(otherSubmission.timeCreated);
    }
}
