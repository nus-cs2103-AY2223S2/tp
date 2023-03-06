package seedu.address.model.contact.exceptions;

import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * Signals that the operation will result in duplicate Contacts (Contacts are considered duplicates if they have the same
 * identity).
 */
public class DuplicateContactException extends RuntimeException {

    public DuplicateContactException() {
        super("Operation will result in duplicate persons");
    }
}
