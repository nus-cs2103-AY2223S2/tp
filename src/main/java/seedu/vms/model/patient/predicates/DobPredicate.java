package seedu.vms.model.patient.predicates;

import java.util.function.Predicate;

import seedu.vms.model.patient.Dob;
import seedu.vms.model.patient.Patient;

/**
 * Tests that a {@code Patient}'s {@code Dob} matches the dob given.
 */
public class DobPredicate implements Predicate<Patient> {
    private final Dob dob;

    public DobPredicate(Dob dob) {
        this.dob = dob;
    }

    @Override
    public boolean test(Patient patient) {
        return dob.equals(patient.getDob());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DobPredicate // instanceof handles nulls
                        && dob.equals(((DobPredicate) other).dob)); // state check
    }

}
