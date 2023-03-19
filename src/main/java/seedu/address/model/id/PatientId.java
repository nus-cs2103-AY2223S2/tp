package seedu.address.model.id;

/**
 * Represents the id of the patient
 * guarantees: details are present and not null, field values are validated, immutable.
 */
public class PatientId extends Id {
    public static final String MESSAGE_CONSTRAINTS =
        "Patient ID should not be blank";
    public static final String VALIDATION_REGEX = ".*";

    public final String id;

    public PatientId(String id) {
        this.id = id;
    }

    /**
     * Returns true if a given string is a valid patient ID.
     */
    public static boolean isValidPatientId(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
