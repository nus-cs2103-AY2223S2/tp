package seedu.address.model.student.exceptions;

/**
 * Signals that the operation will result in lessons with conflicting timeslots
 */
public class ConflictingLessonsException extends RuntimeException {
    public ConflictingLessonsException() {
        super("Operation would result in lessons with overlapping timeslots");
    }
    public ConflictingLessonsException(String message) {
        super(message);
    }
}
