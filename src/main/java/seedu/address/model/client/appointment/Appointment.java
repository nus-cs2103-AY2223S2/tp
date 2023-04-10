package seedu.address.model.client.appointment;

import java.util.Objects;

/**
 * Represents an {@code Appointment} policy that contains {@code AppointmentName} and {@code MeetupDate}.
 * An {@code Appointment} object is immutable, and all fields must be present and not null.
 */
public class Appointment {

    private final AppointmentName appointmentName;
    private final MeetupDate meetupDate;

    /**
     * Every field must be present and not null.
     */
    public Appointment(AppointmentName appointmentName, MeetupDate meetupDate) {
        this.appointmentName = appointmentName;
        this.meetupDate = meetupDate;
    }

    public AppointmentName getAppointmentName() {
        return appointmentName;
    }

    public MeetupDate getMeetupDate() {
        return meetupDate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.getAppointmentName().equals(getAppointmentName())
                && otherAppointment.getMeetupDate().equals(getMeetupDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentName, meetupDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (getAppointmentName().toString() == null || getMeetupDate().toString() == null) {
            return "No appointment set";
        }
        builder.append(getAppointmentName())
                .append("; Meetup Date: ")
                .append(getMeetupDate());
        return builder.toString();
    }
}
