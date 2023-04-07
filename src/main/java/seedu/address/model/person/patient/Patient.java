package seedu.address.model.person.patient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.tag.Tag;
/**
 * Represents a Patient in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient extends Person {
    // Identity fields
    private final Height height;
    private final Weight weight;
    private final Diagnosis diagnosis;
    private final Status status;
    private final Remark remark;
    private final Set<Doctor> doctors;
    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Height height, Weight weight,
                   Diagnosis diagnosis, Status status, Remark remark, Set<Tag> tags, Set<Doctor> doctors) {
        super(name, phone, email, tags);
        requireAllNonNull(height, weight, diagnosis, status, remark, tags, doctors);
        this.height = height;
        this.weight = weight;
        this.diagnosis = diagnosis;
        this.status = status;
        this.remark = remark;
        this.doctors = doctors;
    }
    /**
     * Initializes {@code Patient} object without {@code doctors}.
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Height height, Weight weight,
                   Diagnosis diagnosis, Status status, Remark remark, Set<Tag> tags) {
        super(name, phone, email, tags);
        requireAllNonNull(height, weight, diagnosis, status, remark, tags);
        this.height = height;
        this.weight = weight;
        this.diagnosis = diagnosis;
        this.status = status;
        this.remark = remark;
        this.doctors = new HashSet<>();
    }

    public Height getHeight() {
        return height;
    }
    public Weight getWeight() {
        return weight;
    }
    public Diagnosis getDiagnosis() {
        return diagnosis;
    }
    public Status getStatus() {
        return status;
    }
    public Remark getRemark() {
        return remark;
    }
    public Set<Doctor> getDoctors() {
        return doctors;
    }

    /**
     * Returns true if patient contains supplied doctor.
     *
     * @param doctor a doctor.
     * @return true if patient contains supplied doctor, and false otherwise.
     */
    public boolean hasDoctor(Doctor doctor) {
        return doctors.contains(doctor);
    }

    /**
     * Returns true if patient is assigned to any doctor
     *
     * @return true if patient is assigned to any doctor, and false otherwise.
     */
    public boolean hasDoctors() {
        return doctors.size() > 0;
    }

    /**
     * Removes a doctor if it exists in doctor's doctor list.
     *
     * @param doctor a doctor.
     */
    public void removeDoctorIfAssigned(Doctor doctor) {
        if (this.hasDoctor(doctor)) {
            doctors.remove(doctor);
        }
    }

    /**
     * Assigns a doctor if doctor does not exist in patient's doctor list.
     *
     * @param doctor a doctor.
     */
    public void assignDoctor(Doctor doctor) {
        if (!this.hasDoctor(doctor)) {
            doctors.add(doctor);
        }
    }

    /**
     * Returns true if both patients have the same name.
     * This defines a weaker notion of equality between two patients.
     */
    public boolean isSamePatient(Patient otherPatient) {
        if (otherPatient == this) {
            return true;
        }

        return otherPatient != null
                && otherPatient.getName().equals(getName());
    }

    /**
     * Returns true if both patients have the same identity and data fields.
     * This defines a stronger notion of equality between two patients.
     */
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
                && otherPatient.getHeight().equals(getHeight())
                && otherPatient.getWeight().equals(getWeight())
                && otherPatient.getDiagnosis().equals(getDiagnosis())
                && otherPatient.getStatus().equals(getStatus())
                && otherPatient.getRemark().equals(getRemark())
                && otherPatient.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getHeight(), getWeight(), getDiagnosis(),
                getStatus(), getRemark(), getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Diagnosis: ")
                .append(getDiagnosis())
                .append("; Status: ")
                .append(getStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Doctor> doctors = getDoctors();
        if (!doctors.isEmpty()) {
            builder.append("; Doctors: | ");
            doctors.forEach((Doctor doctor) -> {
                builder.append(doctor.getName()).append(" | ");
            });
        }
        return builder.toString();
    }
}
