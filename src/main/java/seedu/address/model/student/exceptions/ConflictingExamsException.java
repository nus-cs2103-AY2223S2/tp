package seedu.address.model.student.exceptions;

/**
 * Signals that the operation will result in duplicate Exams (Exams are considered duplicates if they have the same
 * description, start time, end time, weightage, status, and grade).
 */
public class ConflictingExamsException extends RuntimeException {
    public ConflictingExamsException() {
        super("The exam clashes with another exam/lesson");
    }
}
