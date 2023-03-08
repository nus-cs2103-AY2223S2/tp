package seedu.address.model.student.exceptions;

/**
 * Signals that the operation will result in duplicate Lessons (Lesson are considered duplicates if they have the same
 * title, startTime, and endTime).
 */
public class DuplicateLessonException extends RuntimeException {
    public DuplicateLessonException() {
        super("Operation would result in duplicate lessons");
    }
}
