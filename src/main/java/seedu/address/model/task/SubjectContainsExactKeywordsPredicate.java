package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Subject} matches exactly the keywords given.
 */
public class SubjectContainsExactKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public SubjectContainsExactKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getSubject().getValue(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubjectContainsExactKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SubjectContainsExactKeywordsPredicate) other).keywords)); // state check
    }
}
