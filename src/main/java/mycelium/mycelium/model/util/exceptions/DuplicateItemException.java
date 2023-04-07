package mycelium.mycelium.model.util.exceptions;

/**
 * Signals that a duplicate item cannot be allowed.
 */
public class DuplicateItemException extends RuntimeException {
    public DuplicateItemException() {
        super("Operation would result in duplicate item.");
    }

    public DuplicateItemException(Object dup) {
        super(String.format("Operation would result in duplicate item %s.", dup));
    }
}
