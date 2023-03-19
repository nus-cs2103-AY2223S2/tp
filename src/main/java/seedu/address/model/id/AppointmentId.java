package seedu.address.model.id;

/**
 * Represents the id of the appointment
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class AppointmentId extends Id {
    public static final String MESSAGE_CONSTRAINTS =
        "Appointment ID should not be blank";
    public static final String VALIDATION_REGEX = ".*";
    private String appointmentId;

    public AppointmentId(String id) {
        this.appointmentId = id;
    }

    @Override
    public String getId() {
        return appointmentId;
    }

    /**
     * Returns true if a given string is a valid appointment ID.
     */
    public static boolean isValidAppointmentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
