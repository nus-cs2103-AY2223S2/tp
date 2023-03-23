package seedu.address.model.id;

/**
 * Represents the id of the appointment
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class AppointmentId extends Id {

    public AppointmentId(String id) {
        super(id);
    }

    public String getAppointmentId() {
        return super.getId();
    }

    /**
     * Returns true if a given string is a valid appointment ID.
     */
    public static boolean isValidAppointmentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
