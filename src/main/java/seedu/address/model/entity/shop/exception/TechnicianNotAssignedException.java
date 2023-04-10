package seedu.address.model.entity.shop.exception;

/**
 * Thrown when the technician has not been assigned to a task.
 */
public class TechnicianNotAssignedException extends Exception {
    public TechnicianNotAssignedException(int techId) {
        super(String.format("Technician %d not assigned yet", techId));
    }
}
