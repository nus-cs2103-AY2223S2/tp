package seedu.vms.model.patient;

import static seedu.vms.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a Patient in the address book.
 * Guarantees: details are present and not null,
 * field values are validated,
 * immutable.
 */
public class Patient {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Dob dateOfBirth;

    // Medical fields
    private final BloodType bloodType;
    private final Set<Allergy> allergies = new HashSet<>();
    private final Set<Vaccine> vaccines = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Dob dob, BloodType bloodType, Set<Allergy> allergies,
            Set<Vaccine> vaccines) {
        requireAllNonNull(name, phone, dob, bloodType, allergies, vaccines);
        this.name = name;
        this.phone = phone;
        this.dateOfBirth = dob;
        this.bloodType = bloodType;
        this.allergies.addAll(allergies);
        this.vaccines.addAll(vaccines);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Dob getDob() {
        return dateOfBirth;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    /**
     * Returns an immutable allergies set,
     * which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Allergy> getAllergy() {
        return Collections.unmodifiableSet(allergies);
    }

    /**
     * Returns an immutable vaccine set,
     * which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Vaccine> getVaccine() {
        return Collections.unmodifiableSet(vaccines);
    }

    /**
     * Returns an immutable allergies set,
     * which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<String> getAllergyAsString() {
        Set<String> allergyStrings = new HashSet<>();
        for (Allergy allergy : allergies) {
            allergyStrings.add(allergy.toString());
        }
        return Collections.unmodifiableSet(allergyStrings);
    }

    /**
     * Returns an immutable vaccine set,
     * which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<String> getVaccineAsString() {
        Set<String> vaccineStrings = new HashSet<>();
        for (Vaccine vaccine : vaccines) {
            vaccineStrings.add(vaccine.toString());
        }
        return Collections.unmodifiableSet(vaccineStrings);
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
                && otherPatient.getDob().equals(getDob())
                && otherPatient.getBloodType().equals(getBloodType())
                && otherPatient.getAllergy().equals(getAllergy())
                && otherPatient.getVaccine().equals(getVaccine());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name,
                phone,
                dateOfBirth,
                bloodType,
                allergies,
                vaccines);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; DateOfBirth: ")
                .append(getDob())
                .append("; BloodType: ")
                .append(getBloodType());

        Set<Allergy> allergySet = getAllergy();
        if (!allergySet.isEmpty()) {
            builder.append("; Allergies: ");
            allergySet.forEach(builder::append);
        }
        Set<Vaccine> vaccineSet = getVaccine();
        if (!vaccineSet.isEmpty()) {
            builder.append("; Vaccines: ");
            vaccineSet.forEach(builder::append);
        }
        return builder.toString();
    }

}
