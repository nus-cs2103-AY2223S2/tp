package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Nric;

/**
 * Represents a Person's appointment in the address book.
 */
public class Appointment {

    private final Nric nric;
    private final Booking booking;

    // todo add another Dr name field to link appmt to a doctor)
    /**
     * Constructs an {@code Appointment}.
     *
     * @param nric Person nric
     * @param booking timing
     */
    public Appointment(Nric nric, Booking booking) {
        requireAllNonNull(nric, booking);
        this.nric = nric;
        this.booking = booking;
    }

    public Booking getBooking() {
        return booking;
    }

    public Nric getPatientNric() {
        return nric;
    }

    /**
     * Checks if this appointment is the same as another appointment.
     * @param otherAppointment
     * @return true if is the same, false otherwise
     */
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
