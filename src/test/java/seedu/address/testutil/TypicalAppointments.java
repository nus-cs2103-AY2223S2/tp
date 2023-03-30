package seedu.address.testutil;

import seedu.address.model.client.appointment.Appointment;

/**
 * A utility class containing a typical set of appointments.
 */
public class TypicalAppointments {
    public static final Appointment DISCUSSION = new AppointmentBuilder().build();

    public static final Appointment EMPTY = new AppointmentBuilder().emptyBuild();

    public static final Appointment COFFEE = new AppointmentBuilder()
            .withAppointmentName("Coffee chat")
            .withMeetupDate("01.01.2024").build();
}
