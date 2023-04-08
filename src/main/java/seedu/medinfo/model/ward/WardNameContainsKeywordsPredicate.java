package seedu.medinfo.model.ward;

import java.util.List;
import java.util.function.Predicate;

import seedu.medinfo.commons.util.StringUtil;
import seedu.medinfo.model.patient.Patient;

/**
 * Tests that a {@code Patient}'s {@code Status} matches any of the keywords
 * given.
 */
public class WardNameContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public WardNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient patient) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(patient.getWard().getNameString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WardNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((WardNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
