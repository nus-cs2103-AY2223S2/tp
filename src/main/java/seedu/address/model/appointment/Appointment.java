package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Nric;

/**
 * Represents a Person's appointment in the address book.
 */
public class Appointment {

    private final Nric nric;
    private final Booking booking;
    private final Nric drNric;

    // todo add another Dr name field to link appmt to a doctor)
    /**
     * Constructs an {@code Appointment}.
     *
     * @param nric Person nric
     * @param booking timing
     */
    public Appointment(Nric nric, Booking booking, Nric drNric) {
        requireAllNonNull(nric, booking);
        this.nric = nric;
        this.booking = booking;
        this.drNric = drNric;
    }

    public Booking getBooking() {
        return booking;
    }

    public Nric getPatientNric() {
        return nric;
    }

    public Nric getDrNric() {
        return drNric;
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
        String otherPatientNric = otherAppointment.getPatientNric().toString();
        String thisPatientNric = nric.toString();
        String otherAppointmentDrNric = otherAppointment.getDrNric().toString();
        String thisAppointmentDrNric = otherAppointment.getDrNric().toString();

        return otherPatientNric.equals(thisPatientNric)
                && thisAppointmentDate.equals(otherAppointmentDate)
                && thisAppointmentDrNric.equals(otherAppointmentDrNric);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("NRIC: ")
                .append(getPatientNric())
                .append("; Appointment: ")
                .append(getBooking())
                .append("; Doctor: ")
                .append(getDrNric());
        return builder.toString();
    }
}
