package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Email} matches any of the keywords given.
 */
public class EmailContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public EmailContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsWordIgnoreCase(person.getEmail().toString(), keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((EmailContainsKeywordPredicate) other).keyword)); // state check
    }

}
