package seedu.address.testutil;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Description;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.id.AppointmentId;
import seedu.address.model.patient.Name;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    public static final String DEFAULT_NAME = "Alex Yeoh";
    public static final String DEFAULT_TIMESLOT = "01012023 00:00,01012023 01:00";
    public static final String DEFAULT_DESCRIPTION = "Regular checkup";
    public static final String DEFAULT_APPOINTMENT_ID = "1";
    public static final String DEFAULT_DOCTOR = "Xiao Lu";

    private AppointmentId appointmentId;
    private Name patientName;
    private Timeslot timeslot;
    private Description description;
    private Doctor doctor;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        appointmentId = new AppointmentId(DEFAULT_APPOINTMENT_ID);
        timeslot = new Timeslot(DEFAULT_TIMESLOT);
        description = new Description(DEFAULT_DESCRIPTION);
        patientName = new Name(DEFAULT_NAME);
        doctor = new Doctor(DEFAULT_DOCTOR);
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
     * Sets the {@code Name} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatientName(String patientName) {
        this.patientName = new Name(patientName);
        return this;
    }

    /**
     * Parses the {@code doctor} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDoctor(String doctor) {
        this.doctor = new Doctor(doctor);
        return this;
    }

    public Appointment build() {
        return new Appointment(appointmentId, patientName, timeslot, description, doctor);
    }
}
