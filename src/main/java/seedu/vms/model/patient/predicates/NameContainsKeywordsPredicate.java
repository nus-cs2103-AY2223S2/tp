package seedu.vms.model.patient.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.vms.commons.util.StringUtil;
import seedu.vms.model.patient.Patient;

/**
 * Tests that a {@code Patient}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient patient) {
        return StringUtil.isMatching(patient.getName().toString(), keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
