package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/*
    ContactContainsTagPredicate is a predicate that filters the model based on the tags

    @author Haiqel Bin Hanaffi
 */
public class ContactContainsTagPredicate implements Predicate<Person> {
    // keywords are the different kind of tags
    private final List<String> keywords;

    public ContactContainsTagPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        // perform filtering based on the tag attribute
        for (String filterValue : keywords) {
            Set<Tag> tagSet = person.getTags();
            for(Tag currentTag : tagSet) {
                String currentTagName = currentTag.tagName;
                if (currentTagName.equalsIgnoreCase(filterValue)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsTagPredicate // instanceof handles nulls
                && keywords.equals(((ContactContainsTagPredicate) other).keywords)); // state check
    }
}
