package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code MeetingWithPerson} is a meeting with a given person
 */

public class PolicyExistInPersonPredicate implements Predicate<Person> {
    /**
     * Represents the specified policy name to be used to check against
     * {@code Person} list of {@code Meetings} for matching {@code PolicyTag} names
     */
    private final List<String> keywords;

    public PolicyExistInPersonPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
            .anyMatch(keyword -> person.getTags().stream()
                .filter(policy -> policy.tagName.contains(keyword)).count() > 0);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PolicyExistInPersonPredicate // instanceof handles nulls
            && keywords.equals(((PolicyExistInPersonPredicate) other).keywords)); // state check
    }
}
