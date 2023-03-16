package seedu.address.model.task;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.List;
import java.util.function.Predicate;

public class TaskDescriptionContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskDescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream().anyMatch(keyword -> StringUtil
                .containsWordIgnoreCase(task.getDescription().fullTaskDescription, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TaskDescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }
}
