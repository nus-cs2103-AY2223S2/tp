package seedu.address.model.person.exceptions;

import seedu.address.commons.exceptions.DuplicateObjectException;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicatePersonException extends DuplicateObjectException {

    /**
     * Constructs a new duplicate person exception.
     */
    public DuplicatePersonException() {
        super("persons");
    }
}
