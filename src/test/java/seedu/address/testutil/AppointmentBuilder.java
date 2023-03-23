package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Description;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.id.AppointmentId;
import seedu.address.model.id.PatientId;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    public static final String DEFAULT_TIMESLOT = "01012023 00:00,01012023 01:00";
    public static final String DEFAULT_DESCRIPTION = "Regular checkup";
    public static final String DEFAULT_PATIENT_ID = "1";
    public static final String DEFAULT_APPOINTMENT_ID = "1";

    private AppointmentId appointmentId;
    private Timeslot timeslot;
    private Description description;
    private PatientId patientId;
    private Set<Tag> tags;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        appointmentId = new AppointmentId(DEFAULT_APPOINTMENT_ID);
        timeslot = new Timeslot(DEFAULT_TIMESLOT);
        description = new Description(DEFAULT_DESCRIPTION);
        patientId = new PatientId(DEFAULT_PATIENT_ID);
        tags = new HashSet<>();
    }

    /**
     * Sets the {@code AppointmentId} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAppointmentId(String appointmentId) {
        this.appointmentId = new AppointmentId(appointmentId);
        return this;
    }

    /**
     * Sets the {@code Timeslot} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withTimeslot(String timeslot) {
        this.timeslot = new Timeslot(timeslot);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code PatientId} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatientId(String patientId) {
        this.patientId = new PatientId(patientId);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Appointment build() {
        return new Appointment(appointmentId, timeslot, description, patientId, tags);
    }
}
