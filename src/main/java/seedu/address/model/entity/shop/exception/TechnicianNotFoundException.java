package seedu.address.model.entity.shop.exception;

/**
 * Thrown when technician not found in the shop
 */
public class TechnicianNotFoundException extends Exception {
    public TechnicianNotFoundException(int id) {
        super(String.format("Technician %d does not exist", id));
    }
}
