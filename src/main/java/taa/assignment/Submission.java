package taa.assignment;

import taa.model.student.Student;

import java.util.Date;

/**
 * An assignment submission
 */
public class Submission implements Comparable<Submission> {
    private boolean isGraded = false;
    private int marks = 0;
    private Student student;
    private Date time;

    /**
     * @param student The student who made this submission.
     */
    public Submission(Student student) {
        this.student = student;
        this.time = new Date();
    }

    /**
     * @param marks
     */
    public void grade(int marks) {
        isGraded = true;
        this.marks = marks;
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
