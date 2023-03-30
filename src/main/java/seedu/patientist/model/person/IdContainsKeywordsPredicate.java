package seedu.patientist.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.patientist.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code IdNumber} matches any of the keywords given.
 */
public class IdContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public IdContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(person.getIdNumber().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof IdContainsKeywordsPredicate)
                && keywords.equals(((IdContainsKeywordsPredicate) other).keywords);
    }
}
