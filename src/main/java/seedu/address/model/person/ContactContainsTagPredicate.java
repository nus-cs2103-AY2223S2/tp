package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/*
    ContactContainsTagPredicate is a predicate that filters the model based on the tags

    @author Haiqel Bin Hanaffi
 */
public class ContactContainsTagPredicate implements Predicate<Person> {
    // keywords are the different kind of tags
    private final Set<Tag> tagSet;

    public ContactContainsTagPredicate(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public boolean test(Person person) {
        // perform filtering based on the tag attribute
        Set<Tag> personTags = person.getTags();
        Set<Tag> tempTagsSet = new HashSet<>(this.tagSet);
        tempTagsSet.removeAll(personTags);
        boolean isTagsExists = tempTagsSet.isEmpty();
        if (!isTagsExists) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsTagPredicate // instanceof handles nulls
                && tagSet.equals(((ContactContainsTagPredicate) other).tagSet)); // state check
    }
}
