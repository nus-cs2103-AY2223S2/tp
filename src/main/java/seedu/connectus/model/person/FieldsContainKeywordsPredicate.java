package seedu.connectus.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.connectus.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s information fields matches any of the keywords given.
 */
public class FieldsContainKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public FieldsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream().anyMatch(keyword ->
                StringUtil.containsKeywordsListIgnoreCase(person.getAllFieldsAsString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FieldsContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((FieldsContainKeywordsPredicate) other).keywords)); // state check
    }
}
