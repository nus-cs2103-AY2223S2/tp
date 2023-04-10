package codoc.model.person;

import static codoc.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.List;
import java.util.function.Predicate;

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
                .allMatch(keyword -> person.getYear().year.toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof YearContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((YearContainsKeywordsPredicate) other).keywords)); // state check
    }
    @Override
    public String toString() {
        return PREFIX_YEAR + keywords.stream().reduce("", String::concat);
    }

}
