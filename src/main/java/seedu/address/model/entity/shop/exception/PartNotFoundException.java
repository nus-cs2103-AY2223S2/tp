package seedu.address.model.entity.shop.exception;

// Thrown when part not found in the shop
public class PartNotFoundException extends Exception {
    public PartNotFoundException(String partName) {
        super(String.format("Part %s does not exist", partName));
    }
}
