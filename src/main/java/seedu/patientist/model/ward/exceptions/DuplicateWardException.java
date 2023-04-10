package seedu.patientist.model.ward.exceptions;

/**
 * Signals that an operation would result in duplicate wards. Wards are equal iff they share the same name.
 */
public class DuplicateWardException extends RuntimeException {
    public DuplicateWardException() {
        super("Operation would result in duplicate wards.");
    }
}
