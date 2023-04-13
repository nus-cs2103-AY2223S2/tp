package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    private final Nric nric;
    private final Role role;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private final ArrayList<Appointment> patientAppointments = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Nric nric, Address address, Set<Tag> tags,
                  ArrayList<Appointment> patientAppointments, Role role) {
        requireAllNonNull(name, phone, email, nric, address, tags, patientAppointments, role);

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nric = nric;
        this.address = address;
        this.tags.addAll(tags);
        this.patientAppointments.addAll(patientAppointments);
        this.role = role;

    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Nric getNric() {
        return nric;
    }

    public Address getAddress() {
        return address;
    }

    public Role getRole() {
        return role;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns a list of Appointments.
     */
    public ArrayList<Appointment> getPatientAppointments() {
        return patientAppointments;
    }

    /**
     * Returns true if both persons have the same NRIC.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getNric().equals(getNric());
    }

    /**
     * Returns true if both persons have the same NRIC.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePersonByNric(Nric otherNric) {
        if (otherNric == this.getNric()) {
            return true;
        }

        return otherNric != null
                && otherNric.equals(this.getNric());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getNric().equals(getNric())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getPatientAppointments().equals(getPatientAppointments());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Nric: ")
                .append(getNric())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        ArrayList<Appointment> appointments = getPatientAppointments();
        if (!appointments.isEmpty()) {
            builder.append("; Appointments: ");
            appointments.forEach(builder::append);
        }

        return builder.toString();
    }

    /**
     * Returns true if a person is a doctor.
     * Returns false otherwise.
     */
    public boolean isDoctor() {
        return role.toString().equals("Doctor");
    }

    /**
     * Returns true if a person is a patient.
     * Returns false otherwise.
     */
    public boolean isPatient() {
        return role.toString().equals("Patient");
    }

    /**
     * Adds an appointment for the Patient.
     * @param appointment
     */
    public void addPatientAppointment(Appointment appointment) {
        patientAppointments.add(appointment);
    }

    /**
     * Gets size of appointment list.
     * @return appointment size
     */
    public int getAppointmentSize() {
        return this.patientAppointments.size();
    }
}
