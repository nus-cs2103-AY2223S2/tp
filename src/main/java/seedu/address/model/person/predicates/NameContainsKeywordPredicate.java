package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final String keyword;

    /**
     * Constructor that takes in a list of keywords
     * @param keywords The list of keywords for the predicate
     */
    public NameContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
        this.keyword = null;
    }

    /**
     * Constructor that takes in a singular {@code String} keyword
     * @param keyword The keyword for the predicate
     */
    public NameContainsKeywordPredicate(String keyword) {
        this.keywords = null;
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        if (keywords != null) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        }
        return StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((NameContainsKeywordPredicate) other).keyword)); // state check
    }

}
