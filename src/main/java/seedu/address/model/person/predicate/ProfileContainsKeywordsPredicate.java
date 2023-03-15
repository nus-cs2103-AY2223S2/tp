package seedu.address.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code GitHubProfile} matches any of the keywords given.
 */
public class ProfileContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ProfileContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getProfile().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProfileContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ProfileContainsKeywordsPredicate) other).keywords)); // state check
    }

}
