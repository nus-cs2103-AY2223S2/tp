package seedu.address.model.person.doctor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.tag.Tag;
/**
 * Represents a Doctor in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Doctor extends Person {
    // Identity fields
    private final Specialty specialty;
    private final Yoe yoe;
    private final Set<Patient> patients;
    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Phone phone, Email email,
                  Specialty specialty, Yoe yoe, Set<Tag> tags, Set<Patient> patients) {
        super(name, phone, email, tags);
        requireAllNonNull(name, phone, email, specialty, yoe, tags, patients);
        this.specialty = specialty;
        this.yoe = yoe;
        this.patients = patients;
    }

    /**
     * Initializes {@code Doctor} object without {@code patients}.
     * Every field must be present and not null.
     */
    public Doctor(Name name, Phone phone, Email email, Specialty specialty, Yoe yoe, Set<Tag> tags) {
        super(name, phone, email, tags);
        requireAllNonNull(name, phone, email, specialty, yoe, tags);
        this.specialty = specialty;
        this.yoe = yoe;
        this.patients = new HashSet<>();
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public Yoe getYoe() {
        return yoe;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public boolean hasPatients() {
        return patients.size() > 0;
    }

    /**
     * Returns true if doctor contains supplied patient.
     *
     * @param patient a patient.
     */
    public boolean hasPatient(Patient patient) {
        return patients.contains(patient);
    }

    /**
     * Adds a patient if it does not exist in doctor's patient list.
     *
     * @param patient a patient.
     */
    public void assignPatient(Patient patient) {
        requireNonNull(patient);
        if (!this.hasPatient(patient)) {
            patients.add(patient);
        }
    }

    /**
     * Removes a patient if it exists in doctor's patient list.
     *
     * @param patient a patient.
     */
    public void removePatientIfAssigned(Patient patient) {
        if (this.hasPatient(patient)) {
            patients.remove(patient);
        }
    }

    /**
     * Returns true if both doctors have the same name.
     * This defines a weaker notion of equality between two doctors.
     */
    public boolean isSameDoctor(Doctor otherDoctor) {
        if (otherDoctor == this) {
            return true;
        }

        return otherDoctor != null
                && otherDoctor.getName().equals(getName());
    }

    /**
     * Returns true if both doctors have the same identity and data fields.
     * This defines a stronger notion of equality between two doctors.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Doctor)) {
            return false;
        }

        Doctor otherDoctor = (Doctor) other;
        return otherDoctor.getName().equals(getName())
                && otherDoctor.getPhone().equals(getPhone())
                && otherDoctor.getEmail().equals(getEmail())
                && otherDoctor.getSpecialty().equals(getSpecialty())
                && otherDoctor.getYoe().equals(getYoe())
                && otherDoctor.getTags().equals(getTags())
                && otherDoctor.getPatients().equals(getPatients());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getSpecialty(), getYoe(), getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Specialty: ")
                .append(getSpecialty())
                .append("; YOE: ")
                .append(getYoe());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Patient> patients = getPatients();
        if (!patients.isEmpty()) {
            builder.append("; Patients: | ");
            patients.forEach((Patient patient) -> {
                builder.append(patient.getName() + " | ");
            });
        }
        return builder.toString();
    }
}
