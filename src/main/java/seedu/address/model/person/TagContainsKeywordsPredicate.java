package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Nric} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    } {
    }

    @Override
    public boolean test(Person person) {
        Tag[] tagsArray = person.getTags().toArray(new Tag[0]);

        for (int i = 0; i < tagsArray.length; i++) {
            String currentTag = tagsArray[i].toString();
            if (keywords.stream()
                    .anyMatch(keyword -> StringUtil
                            .containsWordIgnoreCase(currentTag.substring(1, currentTag.length() - 1), keyword))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }
}
