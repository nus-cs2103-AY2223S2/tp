package seedu.careflow.model.patient.exceptions;

/**
 * Signals that the operation will result in duplicate Patients' IC.
 */
public class DuplicatePatientIcException extends RuntimeException {
    public DuplicatePatientIcException() {
        super("Operation would result in duplicate patients' IC");
    }
}
