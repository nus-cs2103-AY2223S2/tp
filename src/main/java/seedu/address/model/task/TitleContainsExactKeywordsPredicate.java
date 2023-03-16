package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Title} matches exactly the keywords given.
 */
public class TitleContainsExactKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TitleContainsExactKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getTitle().getValue(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleContainsExactKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TitleContainsExactKeywordsPredicate) other).keywords)); // state check
    }
}
