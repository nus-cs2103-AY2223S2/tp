package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the groups given.
 */
public class TagContainsGroupsPredicate implements Predicate<Person> {
    private final List<String> groups;

    public TagContainsGroupsPredicate(List<String> groups) {
        this.groups = groups;
    }

    @Override
    public boolean test(Person person) {
        return groups.stream()
                .anyMatch(keyword -> person.getTags().contains(new Tag(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsGroupsPredicate // instanceof handles nulls
                && groups.equals(((TagContainsGroupsPredicate) other).groups)); // state check
    }

    @Override
    public String toString() {
        return groups.stream().collect(Collectors.joining(" , "));
    }
}
