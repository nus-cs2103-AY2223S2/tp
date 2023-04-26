package seedu.medinfo.model.patient;

import java.util.List;
import java.util.function.Predicate;

import seedu.medinfo.commons.util.StringUtil;

/**
 * Tests that a {@code Patient}'s {@code NRIC} matches any of the keywords
 * given.
 */
public class NricContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public NricContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient patient) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(patient.getNric().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NricContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NricContainsKeywordsPredicate) other).keywords)); // state check
    }

}
