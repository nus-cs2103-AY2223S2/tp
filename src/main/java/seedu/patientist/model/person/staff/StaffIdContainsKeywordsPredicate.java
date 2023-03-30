package seedu.patientist.model.person.staff;

import java.util.List;

import seedu.patientist.model.person.IdContainsKeywordsPredicate;
import seedu.patientist.model.person.Person;

/**
 * Tests that a {@code Staff}'s {@code IdNumber} matches any of the keywords given.
 */
public class StaffIdContainsKeywordsPredicate extends IdContainsKeywordsPredicate {
    public StaffIdContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        if (!(person instanceof Staff)) {
            return false;
        }
        return super.test(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof StaffIdContainsKeywordsPredicate)
                && super.equals(other);
    }
}
