package seedu.techtrack.model.role;

import java.util.List;
import java.util.function.Predicate;

import seedu.techtrack.commons.util.StringUtil;

/**
 * Tests that a {@code Role}'s {@code Company} matches any of the keywords given.
 */
public class CompanyContainsKeywordsPredicate implements Predicate<Role> {
    private final List<String> keywords;

    public CompanyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Role role) {
        return keywords.stream()
                      .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(role.getCompany().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CompanyContainsKeywordsPredicate) other).keywords)); // state check
    }
}
