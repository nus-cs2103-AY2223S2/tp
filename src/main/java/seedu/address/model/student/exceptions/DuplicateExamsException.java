package seedu.address.model.student.exceptions;

/**
 * Signals that the operation will result in duplicate Exams (Exams are considered duplicates if they have the same
 * description, start time, end time, weightage, status, and grade).
 */
public class DuplicateExamsException extends RuntimeException {
    public DuplicateExamsException() {
        super("The exam already exists");
    }
}
