package seedu.address.model.id;

/**
 * Represents the id of the patient
 * guarantees: details are present and not null, field values are validated, immutable.
 */
public class PatientId extends Id {

    /**
     * Constructs a {@code PatientId}.
     *
     * @param id A valid ID.
     */
    public PatientId(String id) {
        super(id);
    }

    public String getPatientId() {
        return super.getId();
    }

    /**
     * Returns true if a given string is a valid patient ID.
     */
    public static boolean isValidPatientId(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
