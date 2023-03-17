package seedu.socket.model.person.predicate;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.socket.commons.util.StringUtil;
import seedu.socket.model.person.Person;
import seedu.socket.model.person.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Language} matches any of the keywords given.
 */
public class FindCommandTagPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public FindCommandTagPredicate(List<String> keywords) {
        this.keywords = keywords;
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
                || (other instanceof FindCommandTagPredicate // instanceof handles nulls
                && keywords.equals(((FindCommandTagPredicate) other).keywords)); // state check
    }

}
