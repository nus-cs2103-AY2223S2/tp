package seedu.address.model.entity.shop.exception;

/**
 * Thrown when no field is edited.
 */
public class NoFieldEditedException extends Exception {
    public NoFieldEditedException() {
        super("At least one field to edit must be provided.");
    }
}
