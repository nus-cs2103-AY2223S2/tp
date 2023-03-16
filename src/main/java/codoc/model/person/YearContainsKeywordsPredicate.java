package codoc.model.person;

import java.util.List;
import java.util.function.Predicate;

import codoc.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Year} matches any of the keywords given.
 */
public class YearContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public YearContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getYear().year, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof YearContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((YearContainsKeywordsPredicate) other).keywords)); // state check
    }

}
