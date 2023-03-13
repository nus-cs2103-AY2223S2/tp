package seedu.address.model.appointment;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Name;

public class Appointment {

    public static final String MESSAGE_CONSTRAINTS = "Appointment booking should be of the format DD-MM-YYYY HH:MM" +
            "and adhere to day and month conventions. 'DD' should range from 0-31 and 'MM' should range from 0-12.";
    public static final String VALIDATION_REGEX =
            "^(0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-\\\\d{4} (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$\n";
    private final Name name; // to update to NRIC
    private final Booking booking;
    // to add another Dr name field to link appmt to a doctor)
    public Appointment(Name name, Booking booking) {
        requireAllNonNull(name, booking);
        //String bookingStr = booking.getBookingDate();
        //checkArgument(isValidAppointment(bookingStr), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.booking = booking;
    }

    public Booking getBooking() { return booking; }

    public Name getName() { return name; }
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        String thisAppointmentDate = getBooking().getBookingDate();
        String otherAppointmentDate = otherAppointment.getBooking().getBookingDate();
        return otherAppointment != null
                && thisAppointmentDate.equals(otherAppointmentDate);
    }

    public static boolean isValidAppointment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

}
