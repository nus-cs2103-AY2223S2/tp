package seedu.address.model.pair.exceptions;

/**
 * Signals that the operation will result in duplicate Pairs (Pairs are considered duplicates if they have the same
 * identity).
 */
public class DuplicatePairException extends RuntimeException {
    public DuplicatePairException() {
        super("Operation would result in duplicate pairs");
    }
}
