package seedu.socket.model.person.predicate;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.socket.commons.util.StringUtil;
import seedu.socket.model.person.Person;
import seedu.socket.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Language} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns true if Tag Predicate is empty.
     *
     * @return {@code true} if Tag Predicate is empty.
     */
    public boolean isEmpty() {
        return keywords.isEmpty();
    }

    @Override
    public boolean test(Person person) {
        Set<Tag> tags = person.getTags();
        return keywords.stream()
                .anyMatch(keyword -> tags.stream().anyMatch(
                        language -> StringUtil.containsWordIgnoreCase(language.tagName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
