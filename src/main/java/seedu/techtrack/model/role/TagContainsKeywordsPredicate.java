package seedu.techtrack.model.role;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.techtrack.commons.util.StringUtil;

/**
 * Tests that a {@code Role}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Role> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    private String cleanseForMatch(String dirtyString) {
        dirtyString = dirtyString.replace("[", "");
        dirtyString = dirtyString.replace("]", "");
        String cleansedString = dirtyString.replace(",", "");
        return cleansedString;
    }

    @Override
    public boolean test(Role role) {
        String tagString = Arrays.toString(role.getTags().toArray(new seedu.techtrack.model.util.tag.Tag[0]));
        String cleansedTags = cleanseForMatch(tagString);
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        cleansedTags , keyword)
                );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }
}
