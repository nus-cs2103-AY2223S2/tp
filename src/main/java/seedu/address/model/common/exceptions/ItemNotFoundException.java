package seedu.address.model.common.exceptions;

/**
 * The exception thrown when an operation cannot find the specified item.
 */
public class ItemNotFoundException extends RuntimeException {
    /**
     * Constructs a new {@code ItemNotFoundException} with the specified detail
     * {@code item}.
     *
     * @param item the item that was not found
     */
    public ItemNotFoundException(Object item) {
        super("Item of class " + item.getClass().getSimpleName() + "not found: " + item);
    }
}
