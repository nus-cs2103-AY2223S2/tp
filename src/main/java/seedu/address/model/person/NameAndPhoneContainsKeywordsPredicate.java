package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} and {@code Phone Number} match any of the keywords given.
 */

public class NameAndPhoneContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywordsContainsNameAndPhone;

    public NameAndPhoneContainsKeywordsPredicate(List<String> keywordsContainsNameAndPhone) {
        this.keywordsContainsNameAndPhone = keywordsContainsNameAndPhone;
    }

    @Override
    public boolean test(Person person) {
        boolean result = keywordsContainsNameAndPhone.stream()
                .allMatch(keyword -> (StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword))
                        || (StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword)));
        return result;
    }
}
