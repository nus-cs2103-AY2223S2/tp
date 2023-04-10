package seedu.address.model.entity.shop.exception;

/**
 * Thrown when plate number is already in use
 */
public class DuplicatePlateNumberException extends Exception {
    public DuplicatePlateNumberException(String plateNumber) {
        super(String.format("Plate number %s already in use", plateNumber));
    }
}
