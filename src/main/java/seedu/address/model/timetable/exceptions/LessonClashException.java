package seedu.address.model.timetable.exceptions;

/**
 * Represents an error where timing is not suitable for a module to fit into a time slot.
 */
public class LessonClashException extends RuntimeException {
    public LessonClashException(String errorMessage) {
        super(errorMessage);
    }
}
