package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.tag.Tag;

/**
 * Represents a Patient in MediConnect.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient extends Person {
    private final ArrayList<Appointment> patientAppointments;
    private final Set<Prescription> prescriptions;

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Nric nric, Address address, Set<Prescription> prescriptions,
                   Set<Tag> tags, ArrayList<Appointment> patientAppointments, Role role) {
        super(name, phone, email, nric, address, tags, patientAppointments, role);
        requireAllNonNull(name, phone, email, address, prescriptions, patientAppointments, role);
        this.prescriptions = prescriptions;
        this.patientAppointments = patientAppointments;
    }

    /**
     * Returns true if both Patient have the same NRIC.
     * This defines a weaker notion of equality between two patients.
     */
    public boolean isSamePatient(Patient otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getNric().equals(getNric());
    }

    /**
     * Returns a copy of the prescriptions
     * @return a copy of the patient's set of prescriptions
     */
    public Set<Prescription> getPrescriptions() {
        return new HashSet<>(prescriptions);
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
                && otherPatient.getPrescriptions().equals(getPrescriptions())
                && otherPatient.getTags().equals(getTags())
                && otherPatient.getPatientAppointments().equals(getPatientAppointments());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(super.toString());

        Set<Prescription> prescriptions = getPrescriptions();
        if (!prescriptions.isEmpty()) {
            builder.append("; Prescriptions: ");
            prescriptions.stream().map(Prescription::toString).sorted().forEach(builder::append);
        }

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
     * Deletes an appointment for the Patient.
     */
    public Appointment deletePatientAppointment(int index) {
        return patientAppointments.remove(index);
    }

    /**
     * Get the patient's appointments as a String in list form.
     * @return list of patient's appointment bookings in String
     */
    public String patientAppointmentstoString() {
        ArrayList<Appointment> patientAppointments = getPatientAppointments();
        String string = "";
        int count = 1;
        for (Appointment appointment : patientAppointments) {
            String appointmentBooking = appointment.getBooking().toString();
            string += count + ". " + appointmentBooking + "; " + appointment.getDrNric().toString() + "\n";
            count++;
        }
        return string;
    }
}
