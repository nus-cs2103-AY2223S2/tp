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
    private final NRIC nric;
    private final Name name;

    // Data fields
    private Status status = Status.GRAY;

    /**
     * Every field must be present and not null.
     */
    public Patient(NRIC nric, Name name) {
        requireAllNonNull(nric, name);
        this.nric = nric;
        this.name = name;
    }

    public Patient(NRIC nric, Name name, Status status) {
        requireAllNonNull(nric, name, status);
        this.nric = nric;
        this.name = name;
        this.status = status;
    }

    public NRIC getNric() {
        return nric;
    }

    public Name getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) {
        requireAllNonNull(status);
        status = newStatus;
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
                && otherPatient.getStatus().equals(getStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(nric, name, status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getNric())
                .append("; Name: ")
                .append(getName())
                .append("; Status: ")
                .append(getStatus());

        return builder.toString();
    }
}
