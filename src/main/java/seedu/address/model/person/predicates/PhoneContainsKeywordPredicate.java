package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Phone} matches any of the keywords given.
 */
public class PhoneContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public PhoneContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }
    public boolean test(Person person) {
        return StringUtil.containsWordIgnoreCase(person.getPhone().toString(), keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PhoneContainsKeywordPredicate // instanceof handles nulls
            && keyword.equals(((PhoneContainsKeywordPredicate) other).keyword)); // state check
    }

}

