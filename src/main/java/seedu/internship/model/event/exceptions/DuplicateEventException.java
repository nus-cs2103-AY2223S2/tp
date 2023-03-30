package seedu.internship.model.event.exceptions;

/**
 * Signals that the operation will result in duplicate Internships (Internships are considered duplicates if they have
 * the same Position and Company Name). TinS assigns an auto-generated ID to the internships to make them distinct.
 */
public class DuplicateEventException extends RuntimeException {
    public DuplicateEventException() {
        super("Operation would result in duplicate events");
    }
}

