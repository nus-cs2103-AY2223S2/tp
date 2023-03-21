package seedu.address.model.event.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class SortComparatorException extends RuntimeException {
    public SortComparatorException() {
        super("Invalid sort type, unable to return sort comparator");
    }
}
