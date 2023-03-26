package seedu.address.model.person;

import static javafx.beans.binding.Bindings.isNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import javax.swing.text.html.Option;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.prescription.Medication;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.tag.Tag;

/**
 * Represents a Patient in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient extends Person {
    private final ArrayList<Appointment> patientAppointments;
    private final Prescription prescription;

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Nric nric, Address address, Prescription prescription,
                   Set<Tag> tags, ArrayList<Appointment> patientAppointments) {
        super(name, phone, email, nric, address, tags, patientAppointments);
        requireAllNonNull(name, phone, email, address, tags, patientAppointments);
        this.prescription = prescription;
        this.patientAppointments = patientAppointments;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    /**
     * Returns a list of Appointments.
     */
    public ArrayList<Appointment> getPatientAppointments() {
        return patientAppointments;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return otherPatient.getName().equals(getName())
                && otherPatient.getPhone().equals(getPhone())
                && otherPatient.getEmail().equals(getEmail())
                && otherPatient.getNric().equals(getNric())
                && otherPatient.getAddress().equals(getAddress())
                && otherPatient.getPrescription().equals(getPrescription())
                && otherPatient.getTags().equals(getTags())
                && otherPatient.getPatientAppointments().equals(getPatientAppointments());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(super.toString());

        builder.append("; Prescription: ")
                .append(getPrescription());

        ArrayList<Appointment> appointments = getPatientAppointments();
        if (!appointments.isEmpty()) {
            builder.append("; Appointments: ");
            appointments.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Adds an appointment for the Patient.
     * @param appointment
     */
    public void addPatientAppointment(Appointment appointment) {
        patientAppointments.add(appointment);
    }

    /**
     * Get the patient's appointments as a String in list form.
     * @return list of patient's appointment bookings in String
     */
    public String patientAppointmentstoString() {
        ArrayList<Appointment> patientAppointments = getPatientAppointments();
        String string = "";
        for (Appointment appointment : patientAppointments) {
            String appointmentBooking = appointment.getBooking().toString();
            string += appointmentBooking + "\n";
        }
        return string;
    }

    @Override
    public boolean isPatient() {
        return true;
    }
}
