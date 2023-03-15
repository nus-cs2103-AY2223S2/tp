package taa.assignment;

import taa.model.student.Student;

import java.util.Date;

/**
 * An assignment submission
 */
public class Submission implements Comparable<Submission> {
    private boolean isGraded = false;
    private int marks = 0;
    private Assignment assignment;
    private Student student;
    private Date time;

    /**
     * @param student The student who made this submission.
     */
    public Submission(Student student, Assignment assignment) {
        this.student = student;
        this.assignment = assignment;
        this.time = new Date();
    }

    /**
     * @param marks
     */
    public void grade(int marks) {
        isGraded = true;
        this.marks = marks;
        this.time = new Date();
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
        return this.time.compareTo(otherSubmission.time);
    }
}
