package seedu.address.testutil;

import seedu.address.model.client.appointment.Appointment;
import seedu.address.model.client.appointment.AppointmentName;
import seedu.address.model.client.appointment.MeetupDate;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    public static final String DEFAULT_APPOINTMENT_NAME = "Meetup discussion";
    public static final String DEFAULT_MEETUP_DATE = "01.01.2024";

    private AppointmentName appointmentName;
    private MeetupDate meetupDate;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        appointmentName = new AppointmentName(DEFAULT_APPOINTMENT_NAME);
        meetupDate = new MeetupDate(DEFAULT_MEETUP_DATE);
    }


    /**
     * Initializes the AppointmentBuilder with the data of {@code aptToCopy}.
     */
    public AppointmentBuilder(Appointment aptToCopy) {
        appointmentName = aptToCopy.getAppointmentName();
        meetupDate = aptToCopy.getMeetupDate();
    }

    /**
     * Sets the {@code AppointmentName} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAppointmentName(String name) {
        this.appointmentName = new AppointmentName(name);
        return this;
    }

    /**
     * Sets the {@code MeetupDate} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withMeetupDate(String date) {
        this.meetupDate = new MeetupDate(date);
        return this;
    }

    /**
     * Creates an empty appointments.
     */
    public Appointment emptyBuild() {
        appointmentName = new AppointmentName();
        meetupDate = new MeetupDate();
        return new Appointment(appointmentName, meetupDate);
    }

    public Appointment build() {
        return new Appointment(appointmentName, meetupDate);
    }
}
