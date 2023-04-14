package tfifteenfour.clipboard.logic.predicates;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import tfifteenfour.clipboard.model.task.Task;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class TaskNameContainsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskNameContainsPredicate(String[] keywords) {
        this.keywords = Arrays.asList(keywords);
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> task.getTaskName().toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskNameContainsPredicate // instanceof handles nulls
                && keywords.equals(((TaskNameContainsPredicate) other).keywords)); // state check
    }

}
