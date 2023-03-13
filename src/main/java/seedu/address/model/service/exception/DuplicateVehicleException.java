package seedu.address.model.service.exception;

/**
 * Signals that the operation will result in duplicate Vehicles (Vehicles are considered duplicates if they have the same
 * plate number).
 */
public class DuplicateVehicleException extends RuntimeException {
    public DuplicateVehicleException() {
        super("Operation would result in duplicate vehicles");
    }
}
