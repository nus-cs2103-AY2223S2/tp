package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the groups given.
 */
public class TagContainsGroupsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagContainsGroupsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getTags().contains(new Tag(keyword)));
    }

    @Override
    public String toString() {
        return keywords.stream().collect(Collectors.joining(" , "));
    }
}
