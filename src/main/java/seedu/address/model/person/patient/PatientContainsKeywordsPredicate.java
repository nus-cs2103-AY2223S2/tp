package seedu.address.model.person.patient;

import java.util.function.Predicate;

/**
 * Tests that a {@code Patient's fields} matches the {@code PatientFilter} given.
 */
public class PatientContainsKeywordsPredicate implements Predicate<Patient> {

    private final PatientFilter patientFilter;

    public PatientContainsKeywordsPredicate(PatientFilter patientFilter) {
        this.patientFilter = patientFilter;
    }

    @Override
    public boolean test(Patient patient) {
        return patientFilter.isMatch((Patient) patient);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PatientContainsKeywordsPredicate // instanceof handles nulls
                && patientFilter.equals(((PatientContainsKeywordsPredicate) other).patientFilter)); // state check
    }

}
