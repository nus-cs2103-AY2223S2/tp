package seedu.address.model.id;

/**
 * Represents the id of the appointment
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class AppointmentId extends Id {
    private String id;

    public AppointmentId(String id) {
        this.id = id;
    }
}
