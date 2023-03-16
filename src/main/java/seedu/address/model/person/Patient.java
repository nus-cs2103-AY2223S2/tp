package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Patient in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient extends Person {
    private final ArrayList<Appointment> patientAppointments;
    public Patient(Name name, Phone phone, Email email, Nric nric, Address address, Set<Tag> tags,
                   ArrayList<Appointment> patientAppointments) {
        super(name, phone, email, nric, address, tags);
        requireAllNonNull(name, phone, email, address, tags, patientAppointments);
        this.patientAppointments = patientAppointments;
    }

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
                && otherPatient.getTags().equals(getTags())
                && otherPatient.getPatientAppointments().equals(getPatientAppointments());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        String personString = super.toString();
        builder.append(personString);

        ArrayList<Appointment> appointments = getPatientAppointments();
        if (!appointments.isEmpty()) {
            builder.append("; Appointments: ");
            appointments.forEach(builder::append);
        }
        return personString.toString();
    }

    public void addPatientAppointment(Appointment appointment) {
        patientAppointments.add(appointment);
    }

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
