package seedu.address.model.tag;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.person.Person;


/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the tags given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {


    private final Set<Tag> keywords;

    public TagContainsKeywordsPredicate(Set<Tag> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.isEmpty()) {
            return true;
        }

        Set<Tag> personTags = person.getTags();
        boolean tagsPresent = true;

        for (Tag tag: keywords) {
            tagsPresent = tagsPresent && personTags.contains(tag);
        }

        return tagsPresent;

    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }
}
