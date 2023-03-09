package seedu.address.model.lecture.exceptions;

/**
 * Signals that the operation will result in duplicate Lectures (Lectures are considered duplicates if they have the
 * same name).
 */
public class DuplicateLectureException extends RuntimeException {
    public DuplicateLectureException() {
        super("Operation would result in duplicate lectures");
    }
}
