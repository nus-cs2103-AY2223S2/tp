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

        String thisAppointmentDate = retrieveAppmtDate(this);
        String otherAppointmentDate = retrieveAppmtDate(otherAppointment);
        String thisAppointmentPatient = retrievePatientNric(this);
        String otherAppointmentPatient = retrievePatientNric(otherAppointment);
        String thisAppointmentDr = retrieveDrNric(this);
        String otherAppointmentDr = retrieveDrNric(otherAppointment);

        return thisAppointmentDate.equals(otherAppointmentDate)
                && thisAppointmentPatient.equals(otherAppointmentPatient)
                && thisAppointmentDr.equals(otherAppointmentDr);
    }

    private String retrieveDrNric(Appointment appointment) {
        String thisAppointmentDr = appointment.getDrNric().toString();
        return thisAppointmentDr;
    }


    /**
     * Checks if this appointment slot and the patient is the same as another appointment.
     * @param otherAppointment
     * @return true if is the same, false otherwise
     */
    public boolean isSamePatientAppointmentTime(Appointment otherAppointment) {
        String thisAppointmentDate = retrieveAppmtDate(this);
        String otherAppointmentDate = retrieveAppmtDate(otherAppointment);
        String thisAppointmentPatient = retrievePatientNric(this);
        String otherAppointmentPatient = retrievePatientNric(otherAppointment);

        return thisAppointmentDate.equals(otherAppointmentDate)
                && thisAppointmentPatient.equals(otherAppointmentPatient);
    }

    private String retrievePatientNric(Appointment appointment) {
        String thisAppointmentPatient = appointment.getPatientNric().toString();
        return thisAppointmentPatient;
    }

    private String retrieveAppmtDate(Appointment appointment) {
        String thisAppointmentDate = appointment.getBooking().toString();
        return thisAppointmentDate;
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
