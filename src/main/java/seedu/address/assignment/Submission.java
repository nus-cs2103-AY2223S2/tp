package seedu.address.assignment;

/**
 * An assignment submission
 */
public class Submission {
    boolean isGraded = false;
    int marks = 0;
    int studentId;

    /**
     * @param studentId
     */
    public Submission(int studentId) {
        this.studentId = studentId;
    }

    public void grade(int marks) {
        isGraded = true;
        this.marks = marks;
    }
}
