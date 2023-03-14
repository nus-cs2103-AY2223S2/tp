package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Description;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.patient.Patient;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    public static final String DEFAULT_TIMESLOT = "01012023 00:00,01012023 01:00";
    public static final String DEFAULT_DESCRIPTION = "Regular checkup";
    public static final String DEFAULT_PATIENT = "John";

    private Timeslot timeslot;
    private Description description;
    private Patient patient;
    private Set<Tag> tags;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public AppointmentBuilder() {
        timeslot = new Timeslot(DEFAULT_TIMESLOT);
        description = new Description(DEFAULT_DESCRIPTION);
        PatientBuilder patientBuilder = new PatientBuilder();
        patient = patientBuilder.withName(DEFAULT_PATIENT).build();
        tags = new HashSet<>();
    }

    public Appointment build() {
        return new Appointment(timeslot, description, patient, tags);
    }
}
