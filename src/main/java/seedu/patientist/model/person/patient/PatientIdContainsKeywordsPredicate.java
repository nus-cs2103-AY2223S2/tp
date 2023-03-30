package seedu.patientist.model.person.patient;

import java.util.List;

import seedu.patientist.model.person.IdContainsKeywordsPredicate;
import seedu.patientist.model.person.Person;

/**
 * Tests that a {@code Patient}'s {@code IdNumber} matches any of the keywords given.
 */
public class PatientIdContainsKeywordsPredicate extends IdContainsKeywordsPredicate {
    public PatientIdContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        if (!(person instanceof Patient)) {
            return false;
        }
        return super.test(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof PatientIdContainsKeywordsPredicate)
                && super.equals(other);
    }
}
