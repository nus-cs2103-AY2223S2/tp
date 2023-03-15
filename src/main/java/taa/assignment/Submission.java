package taa.assignment;

/**
 * An assignment submission
 */
public class Submission {
    private boolean isGraded = false;
    private int marks = 0;
    private int studentId;

    /**
     * @param studentId
     */
    public Submission(int studentId) {
        this.studentId = studentId;
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
        return String.format("[%c] Student %d: %d marks.", gradeChar, studentId, marks);
    }
}
