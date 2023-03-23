package seedu.address.model.application;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Application}'s {@code Company Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Application> {
    private final List<String> keywords;

    /**
     * Constructs a predicate with given keywords.
     * @param keywords that the user has inputted.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        assert(keywords != null);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Application application) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        application.getCompanyName().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
