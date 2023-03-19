package seedu.address.model.event.exceptions;

/**
 * Signals that the operation will result in duplicate Consultation (Consultations are considered
 * duplicates if they have the same identity).
 */
public class DuplicateConsultationException extends RuntimeException {
    public DuplicateConsultationException() {
        super("Oh no :-(. You are already consulting other students");
    }
}
