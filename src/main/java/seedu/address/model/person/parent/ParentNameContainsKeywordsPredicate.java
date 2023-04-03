package seedu.address.model.person.parent;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Parent}'s {@code Name} matches any of the keywords given.
 */
public class ParentNameContainsKeywordsPredicate implements Predicate<Parent> {
    private final List<String> keywords;

    public ParentNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Parent parent) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(parent.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other
                instanceof ParentNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ParentNameContainsKeywordsPredicate) other)
                .keywords)); // state check
    }
}
