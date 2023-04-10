package trackr.model.item.exceptions;

/**
 * Signals that the operation will result in duplicate Items (Items are considered duplicates if they have the same
 * identity).
 */
//@@author liumc-sg-reused
public class DuplicateItemException extends RuntimeException {
    public DuplicateItemException(String itemName) {
        super(String.format("Operation would result in duplicate %ss", itemName));
    }
}
