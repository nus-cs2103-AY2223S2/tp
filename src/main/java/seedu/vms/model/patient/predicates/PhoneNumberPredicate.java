package seedu.vms.model.patient.predicates;

import java.util.function.Predicate;

import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.Phone;

/**
 * Tests that a {@code Patient}'s {@code Phone} matches the phone given.
 */
public class PhoneNumberPredicate implements Predicate<Patient> {
    private final Phone phone;

    public PhoneNumberPredicate(Phone phone) {
        this.phone = phone;
    }

    @Override
    public boolean test(Patient patient) {
        return phone.equals(patient.getPhone());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneNumberPredicate // instanceof handles nulls
                        && phone.equals(((PhoneNumberPredicate) other).phone)); // state check
    }

}
