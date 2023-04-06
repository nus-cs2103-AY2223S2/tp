package seedu.address.model.entity.shop.exception;

/**
 * Thrown when part not found in the shop
 */
public class PartNotFoundException extends Exception {
    /**
     * Constructs a new PartNotFoundException with the specified detail {@code part}.
     *
     * @param partName The part that was not found.
     */
    public PartNotFoundException(String partName) {
        super(String.format("Part %s does not exist", partName));
    }
}
