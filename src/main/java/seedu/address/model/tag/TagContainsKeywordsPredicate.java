package seedu.address.model.tag;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

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
