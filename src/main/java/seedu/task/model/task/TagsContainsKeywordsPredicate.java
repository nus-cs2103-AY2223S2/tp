package seedu.task.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.task.commons.util.StringUtil;

/**
 * Tests that any of a {@code Task}'s {@code Tags} matches any of the keywords given.
 */
public class TagsContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keyphrases;

    public TagsContainsKeywordsPredicate(List<String> keyphrases) {
        this.keyphrases = keyphrases;
    }

    @Override
    public boolean test(Task task) {
        return keyphrases.stream().anyMatch(keyphrase ->
                task.getTags().stream().anyMatch(tag -> StringUtil.containsIgnoreCase(tag.tagName, keyphrase)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainsKeywordsPredicate // instanceof handles nulls
                && keyphrases.equals(((TagsContainsKeywordsPredicate) other).keyphrases)); // state check
    }

}
