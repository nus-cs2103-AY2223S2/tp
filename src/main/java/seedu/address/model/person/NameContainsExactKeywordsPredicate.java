package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Subject} matches exactly the keywords given.
 */
public class NameContainsExactKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public NameContainsExactKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsExactKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsExactKeywordsPredicate) other).keywords)); // state check
    }
}
