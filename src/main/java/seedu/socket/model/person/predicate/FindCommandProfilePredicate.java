package seedu.socket.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.socket.commons.util.StringUtil;
import seedu.socket.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code GitHubProfile} matches any of the keywords given.
 */
public class FindCommandProfilePredicate implements Predicate<Person> {
    private final List<String> keywords;

    public FindCommandProfilePredicate(List<String> keywords) {
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
                || (other instanceof FindCommandProfilePredicate // instanceof handles nulls
                && keywords.equals(((FindCommandProfilePredicate) other).keywords)); // state check
    }

}
