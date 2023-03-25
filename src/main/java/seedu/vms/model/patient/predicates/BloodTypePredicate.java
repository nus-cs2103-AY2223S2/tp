package seedu.vms.model.patient.predicates;

import java.util.function.Predicate;

import seedu.vms.model.patient.BloodType;
import seedu.vms.model.patient.Patient;

/**
 * Tests that a {@code Patient}'s {@code BloodType} matches the bloodType given.
 */
public class BloodTypePredicate implements Predicate<Patient> {
    private final BloodType bloodType;

    public BloodTypePredicate(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    @Override
    public boolean test(Patient patient) {
        return bloodType.equals(patient.getBloodType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BloodTypePredicate // instanceof handles nulls
                        && bloodType.equals(((BloodTypePredicate) other).bloodType)); // state check
    }

}
