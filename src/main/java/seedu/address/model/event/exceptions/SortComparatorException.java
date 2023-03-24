package seedu.address.model.event.exceptions;

/**
 * Signals that an invalid sort type is passed to the function and the program is unable to return a sort comparator.
 */
public class SortComparatorException extends RuntimeException {
    public SortComparatorException() {
        super("Invalid sort type, unable to return sort comparator");
    }
}
