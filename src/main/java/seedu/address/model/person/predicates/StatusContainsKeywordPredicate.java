package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Status} matches any of the keywords given.
 */
public class StatusContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public StatusContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsWordIgnoreCase(person.getStatus().toString(), keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatusContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((StatusContainsKeywordPredicate) other).keyword)); // state check
    }

}
