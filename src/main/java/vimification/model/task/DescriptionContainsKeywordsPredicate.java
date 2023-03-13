package vimification.model.task;

import java.util.List;
import java.util.function.Predicate;

import vimification.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Description} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof vimification.model.task.DescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((vimification.model.task.DescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }

}