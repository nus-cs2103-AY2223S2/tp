package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Represents a Doctor in MediConnect.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Doctor extends Person {
    private final ArrayList<Appointment> patientAppointments;

    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Phone phone, Email email, Nric nric, Address address, Set<Tag> tags,
                  ArrayList<Appointment> patientAppointments, Role role) {
        super(name, phone, email, nric, address, tags, patientAppointments, role);
        this.patientAppointments = patientAppointments;
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
     * Deletes an appointment for the Doctor.
     */
    public void deletePatientAppointment(Appointment appointment) {
        patientAppointments.remove(appointment);
    }

}

