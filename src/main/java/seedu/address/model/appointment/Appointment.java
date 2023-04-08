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
        String thisAppointmentPatient = getPatientNric().toString();
        String otherAppointmentPatient = otherAppointment.getPatientNric().toString();
        String thisAppointmentDr = getDrNric().toString();
        String otherAppointmentDr = otherAppointment.getDrNric().toString();

        return thisAppointmentDate.equals(otherAppointmentDate)
                && thisAppointmentPatient.equals(otherAppointmentPatient)
                && thisAppointmentDr.equals(otherAppointmentDr);
    }

    /**
     * Checks if this appointment slot and the patient is the same as another appointment.
     * @param otherAppointment
     * @return true if is the same, false otherwise
     */
    public boolean isSamePatientAppointmentTime(Appointment otherAppointment) {
        String thisAppointmentDate = getBooking().toString();
        String otherAppointmentDate = otherAppointment.getBooking().toString();
        String thisAppointmentPatient = getPatientNric().toString();
        String otherAppointmentPatient = otherAppointment.getPatientNric().toString();

        return thisAppointmentDate.equals(otherAppointmentDate)
                && thisAppointmentPatient.equals(otherAppointmentPatient);
    }

    /**
     * if this appointment slot and the Doctor is the same as another appointment.
     * @param otherAppointment
     * @return true if is the same, false otherwise
     */
    public boolean isSameDrAppointmentTime(Appointment otherAppointment) {
        String thisAppointmentDate = getBooking().toString();
        String otherAppointmentDate = otherAppointment.getBooking().toString();
        String thisAppointmentDoctor = getDrNric().toString();
        String otherAppointmentDoctor = otherAppointment.getDrNric().toString();

        return thisAppointmentDate.equals(otherAppointmentDate)
                && thisAppointmentDoctor.equals(otherAppointmentDoctor);
    }
    @Override
    public String toString() {
        String builder = "NRIC: " + getPatientNric()
                + "; Appointment: " + getBooking()
                + "; Doctor: " + getDrNric();
        return builder;
    }
}
