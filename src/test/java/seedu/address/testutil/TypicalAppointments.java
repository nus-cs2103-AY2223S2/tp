package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AppointmentList;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {
    public static final Appointment FIRST_APPOINTMENT = new AppointmentBuilder()
            .withPatientName("Alex Yeoh").withTimeslot("19032023 08:00,19032023 09:00")
            .withDescription("First appointment").withDoctor("Xiao Lu").build();
    public static final Appointment SECOND_APPOINTMENT = new AppointmentBuilder()
        .withPatientName("Charles Darwin").withTimeslot("20032023 08:00,20032023 09:00")
        .withDescription("Second appointment").withDoctor("Xiao Lu").build();
    public static final Appointment IMPORTANT_APPOINTMENT = new AppointmentBuilder()
            .withPatientName("Bernice Yu").withTimeslot("26032023 11:00,26032023 13:00")
            .withDescription("This is important!").withDoctor("Sidharth Rajesh").build();

    // TODO: Manually added appointments
    // TODO: Manually added - Appointment's details found in {@code CommandTestUtil}

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalAppointments() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical patients.
     */
    public static AppointmentList getTypicalAppointmentList() {
        AppointmentList al = new AppointmentList();
        for (Appointment appointment : getTypicalAppointments()) {
            al.addAppointment(appointment);
        }
        return al;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(FIRST_APPOINTMENT, SECOND_APPOINTMENT, IMPORTANT_APPOINTMENT));
    }
}
