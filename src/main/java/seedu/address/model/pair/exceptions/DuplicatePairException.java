package seedu.address.model.pair.exceptions;

import seedu.address.commons.exceptions.DuplicateObjectException;

/**
 * Signals that the operation will result in duplicate Pairs (Pairs are considered duplicates
 * if its volunteer and elderly have the same identity).
 */
public class DuplicatePairException extends DuplicateObjectException {

    /**
     * Constructs a new duplicate pair exception.
     */
    public DuplicatePairException() {
        super("pairs");
    }
}
