package seedu.address.model.group.exceptions;

/**
 * Signals that the operation will result in duplicate groups in a Person (Groups are considered duplicates if they
 * have the same group name).
 */
public class PersonAlreadyInGroupException extends RuntimeException {
    public PersonAlreadyInGroupException() {
        super("Operation would result in a person having duplicate groups");
    }
}
