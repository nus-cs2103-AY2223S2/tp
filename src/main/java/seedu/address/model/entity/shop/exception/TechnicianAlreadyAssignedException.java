package seedu.address.model.entity.shop.exception;

/**
 * Thrown when the technician is already assigned to a task.
 */
public class TechnicianAlreadyAssignedException extends Exception {
    public TechnicianAlreadyAssignedException(int techId) {
        super(String.format("Technician %d already assigned", techId));
    }
}
