package seedu.address.model.appointment;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;

public class Appointment {

    private final Nric nric;
    private final Booking booking;
    // to add another Dr name field to link appmt to a doctor)
    public Appointment(Nric nric, Booking booking) {
        requireAllNonNull(nric, booking);
        //String bookingStr = booking.getBookingDate();
        //checkArgument(isValidAppointment(bookingStr), MESSAGE_CONSTRAINTS);
        this.nric = nric;
        this.booking = booking;
    }

    public Booking getBooking() { return booking; }

    public Nric getPatientNric() { return nric; }
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }
        String thisAppointmentDate = getBooking().toString();
        String otherAppointmentDate = otherAppointment.getBooking().toString();
        String otherPatientNric = otherAppointment.getPatientNric().toString(); // todo change to nric
        String thisPatientNric = nric.toString();
        return otherPatientNric.equals(thisPatientNric)
                && thisAppointmentDate.equals(otherAppointmentDate);
    }
}
