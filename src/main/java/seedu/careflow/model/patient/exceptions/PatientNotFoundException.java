package seedu.careflow.model.patient.exceptions;

/**
 * Signals that the operation is unable to find the specified patient.
 */
public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException() {
        super("Operation is unable to find the specified patient");
    }
}
