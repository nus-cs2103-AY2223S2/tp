package seedu.medinfo.model.ward.exceptions;

/**
 * Signals that the operation will result in duplicate Wards.
 * Wards are considered duplicates if they have the same name.
 */
public class DuplicateWardException extends RuntimeException {
    public DuplicateWardException() {
        super("Operation would result in duplicate wards");
    }
}
