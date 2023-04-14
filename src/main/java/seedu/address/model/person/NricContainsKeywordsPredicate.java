package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Nric} matches any of the keywords given.
 */
public class NricContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public NricContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    } {
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getNric().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NricContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NricContainsKeywordsPredicate) other).keywords)); // state check
    }
}
