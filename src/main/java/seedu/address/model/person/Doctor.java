package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Represents a Doctor in Mediconnect.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Doctor extends Person {
    private final ArrayList<Appointment> patientAppointments;

    /**
     * Creates Doctor object.
     * @param name
     * @param phone
     * @param email
     * @param nric
     * @param address
     * @param tags
     * @param patientAppointments
     * @param role
     */
    public Doctor(Name name, Phone phone, Email email, Nric nric, Address address, Set<Tag> tags,
                  ArrayList<Appointment> patientAppointments, Role role) {
        super(name, phone, email, nric, address, tags, patientAppointments, role);
        this.patientAppointments = patientAppointments;
    }

    /**
     * Returns true if both Doctors have the same NRIC.
     * This defines a weaker notion of equality between two doctors.
     */
    public boolean isSameDoctor(Doctor otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getNric().equals(getNric());
    }

    /**
     * Returns a list of Appointments.
     */
    public ArrayList<Appointment> getPatientAppointments() {
        return patientAppointments;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(super.toString());

        ArrayList<Appointment> appointments = getPatientAppointments();
        if (!appointments.isEmpty()) {
            builder.append("; Appointments: ");
            appointments.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Adds an appointment for the Doctor.
     * @param appointment
     */
    public void addPatientAppointment(Appointment appointment) {
        patientAppointments.add(appointment);
    }

    /**
     * Get the patient's appointments as a String in list form.
     * @return list of patient's appointment bookings in String
     */
    public String drAppointmentsToString() {
        ArrayList<Appointment> patientAppointments = getPatientAppointments();
        String string = "";
        int count = 1;
        for (Appointment appointment : patientAppointments) {
            String appointmentBooking = appointment.getBooking().toString();
            string += count + ". " + appointmentBooking + "; " + appointment.getPatientNric().toString() + "\n";
            count++;
        }
        return string;
    }

    /**
     * Returns true if both persons have the same NRIC.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameDrByNric(Nric otherNric) {
        if (otherNric == this.getNric()) {
            return true;
        }

        return otherNric != null
                && otherNric.equals(this.getNric());
    }

    /**
     * Deletes an appointment for the Doctor.
     */
    public void deletePatientAppointment(Appointment appointment) {
        patientAppointments.remove(appointment);
    }

}

