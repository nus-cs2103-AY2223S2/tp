package seedu.address.model.entity.shop.exception;

/**
 * Thrown when vehicle not found in the shop
 */
public class VehicleNotFoundException extends Exception {
    public VehicleNotFoundException(int id) {
        super(String.format("Vehicle %d does not exist", id));
    }
}
