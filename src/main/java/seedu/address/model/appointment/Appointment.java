package seedu.address.model.appointment;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class Appointment {

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
        String thisAppointmentDate = getBooking().toString();
        String otherAppointmentDate = otherAppointment.getBooking().toString();
        String otherPatientName = otherAppointment.getName().toString(); // todo change to nric
        String thisPatientName = name.toString();
        return otherPatientName.equals(thisPatientName)
                && thisAppointmentDate.equals(otherAppointmentDate);
    }
}
