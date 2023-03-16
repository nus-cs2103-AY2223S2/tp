package seedu.address.model.opening;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Opening}'s {@code Company} or {@code Position} matches any of the keywords given.
 */
public class CompanyOrPositionContainsKeywordsPredicate implements Predicate<Opening> {
    private final List<String> keywords;

    public CompanyOrPositionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Opening opening) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(opening.getPosition().fullPosition, keyword)
                        || StringUtil.containsWordIgnoreCase(opening.getCompany().fullCompany, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyOrPositionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CompanyOrPositionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
