package seedu.address.model.patient;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Patient}'s {@code Name}, {@code Phone}, {@code Email}, {@code Address} or {@code Tag}s matches
 * any of the keywords given.
 */
public class DetailsContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public DetailsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient patient) {
        return keywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(patient.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DetailsContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((DetailsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
