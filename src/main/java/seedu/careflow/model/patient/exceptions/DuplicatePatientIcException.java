package seedu.careflow.model.patient.exceptions;

public class DuplicatePatientIcException extends RuntimeException {
    public DuplicatePatientIcException() {
        super("Operation would result in duplicate patients' IC");
    }
}
