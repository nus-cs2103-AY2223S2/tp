package seedu.socket.model.person.predicate;

import java.util.Set;
import java.util.function.Predicate;

import seedu.socket.model.person.Person;
import seedu.socket.model.person.tag.Tag;


/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the tags given.
 */
public class listCommandTagPredicate implements Predicate<Person> {

    private final Set<Tag> tags;

    public listCommandTagPredicate(Set<Tag> keywords) {
        this.tags = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (tags.isEmpty()) {
            return true;
        }

        Set<Tag> personTags = person.getTags();
        boolean tagsPresent = true;

        for (Tag tag: tags) {
            tagsPresent = tagsPresent && personTags.contains(tag);
        }

        return tagsPresent;

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof listCommandTagPredicate // instanceof handles nulls
                && tags.equals(((listCommandTagPredicate) other).tags)); // state check
    }
}
