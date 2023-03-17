package seedu.address.model.service.exception;

/**
 * Thrown when part name not found in the part map
 */
public class PartNotFoundException extends RuntimeException {
    public PartNotFoundException(String partName) {
        super(partName + " not found.");
    }
}
