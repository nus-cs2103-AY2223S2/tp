package seedu.address.model.patient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Patient in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Patient {

    // Identity fields
    private final Nric nric;
    private final Name name;

    // Data fields
    private Status status = new Status("GRAY");
    private Ward ward = new Ward("Waiting Room");

    /**
     * Every field must be present and not null.
     */
    public Patient(Nric nric, Name name) {
        requireAllNonNull(nric, name);
        this.nric = nric;
        this.name = name;
    }

    public Patient(Nric nric, Name name, Status status) {
        requireAllNonNull(nric, name, status);
        this.nric = nric;
        this.name = name;
        this.status = status;
    }

    public Patient(Nric nric, Name name, Ward ward) {
        requireAllNonNull(nric, name, ward);
        this.nric = nric;
        this.name = name;
        this.ward = ward;
    }

    public Patient(Nric nric, Name name, Status status, Ward ward) {
        requireAllNonNull(nric, name, status, ward);
        this.nric = nric;
        this.name = name;
        this.status = status;
        this.ward = ward;
    }

    public Nric getNric() {
        return nric;
    }

    public Name getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Ward getWard() {
        return ward;
    }

    public void setStatus(Status newStatus) {
        requireAllNonNull(newStatus);
        status = newStatus;
    }

    public void setWard(Ward newWard) {
        requireAllNonNull(newWard);
        ward = newWard;
    }

    /**
     * Returns true if both patients have the same nric and name.
     * This defines a weaker notion of equality between two patients.
     */
    public boolean isSamePatient(Patient otherPatient) {
        if (otherPatient == this) {
            return true;
        }

        return otherPatient != null
                && otherPatient.getNric().equals(getNric())
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
        return otherPatient.getNric().equals(getNric())
                && otherPatient.getName().equals(getName())
                && otherPatient.getStatus().equals(getStatus())
                && otherPatient.getWard().equals(getWard());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(nric, name, status, ward);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getNric())
                .append("; Name: ")
                .append(getName())
                .append("; Status: ")
                .append(getStatus())
                .append("; Ward: ")
                .append(getWard());

        return builder.toString();
    }
}
