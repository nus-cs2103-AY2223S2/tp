package seedu.vms.model.patient;

import static seedu.vms.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.vms.commons.util.AppUtil;
import seedu.vms.commons.util.StringUtil;
import seedu.vms.model.GroupName;

/**
 * Represents a Patient in the patient manager.
 * Guarantees: details are present and not null,
 * field values are validated,
 * immutable.
 */
public class Patient {
    public static final int LIMIT_ALLERGIES = 100;
    public static final int LIMIT_VACCINES = 30;

    private static final String FORMAT_IVE_MESSAGE = "The following patient constraints have been violated\n%s";

    public static final String MESSAGE_ALLERGIES_CONSTRAINTS = String
            .format("Only a maximum of %d allergies are allowed", LIMIT_ALLERGIES);
    public static final String MESSAGE_VACCINES_CONSTRAINTS = String
            .format("Only a maximum of %d vaccines are allowed", LIMIT_VACCINES);

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Dob dateOfBirth;

    // Medical fields
    private final BloodType bloodType;
    private final Set<GroupName> allergies = new HashSet<>();
    private final Set<GroupName> vaccines = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Dob dob, BloodType bloodType, Set<GroupName> allergies,
            Set<GroupName> vaccines) {
        requireAllNonNull(name, phone, dob, bloodType, allergies, vaccines);

        AppUtil.checkArgument(AppUtil.isWithinLimit(allergies, LIMIT_ALLERGIES), MESSAGE_ALLERGIES_CONSTRAINTS);
        AppUtil.checkArgument(AppUtil.isWithinLimit(vaccines, LIMIT_VACCINES), MESSAGE_VACCINES_CONSTRAINTS);
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
    public Set<GroupName> getAllergy() {
        return Collections.unmodifiableSet(allergies);
    }

    /**
     * Returns an immutable vaccine set,
     * which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<GroupName> getVaccine() {
        return Collections.unmodifiableSet(vaccines);
    }

    /**
     * Returns an immutable allergies set,
     * which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<String> getAllergyAsString() {
        Set<String> allergyStrings = new HashSet<>();
        for (GroupName allergy : allergies) {
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
        for (GroupName vaccine : vaccines) {
            vaccineStrings.add(vaccine.toString());
        }
        return Collections.unmodifiableSet(vaccineStrings);
    }

    public Patient setVaccination(Set<GroupName> updatedVaccines) {
        return new Patient(name, phone, dateOfBirth, bloodType, allergies, updatedVaccines);
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
     * Validates the parameters and returns the error message if there are any violations
     *
     * @param allergies
     * @param vaccines
     * @return Optional String that contains the errors
     */
    public static Optional<String> validateParams(Optional<Set<GroupName>> allergies,
            Optional<Set<GroupName>> vaccines) {
        ArrayList<String> errMessages = new ArrayList<>();

        if (allergies.isPresent() && !AppUtil.isWithinLimit(allergies.get(), Patient.LIMIT_ALLERGIES)) {
            errMessages.add(Patient.MESSAGE_ALLERGIES_CONSTRAINTS);
        }
        if (vaccines.isPresent() && !AppUtil.isWithinLimit(vaccines.get(), Patient.LIMIT_VACCINES)) {
            errMessages.add(Patient.MESSAGE_VACCINES_CONSTRAINTS);
        }

        if (errMessages.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(StringUtil.formatErrorMessage(errMessages, FORMAT_IVE_MESSAGE));
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
        builder.append(getName());

        return builder.toString();
    }
}
