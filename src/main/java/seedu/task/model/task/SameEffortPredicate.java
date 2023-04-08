package seedu.task.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Effort} matches input effort level.
 */
public class SameEffortPredicate implements Predicate<Task> {

    private Effort effort;

    /**
     * Creates a {@code SameEffortPredicate} with an effort to be matched with.
     * @param effort match tasks that has this effort
     */
    public SameEffortPredicate(Effort effort) {
        this.effort = effort;
    }
    @Override
    public boolean test(Task task) {
        return task.getEffort().equals(this.effort);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SameEffortPredicate // instanceof handles nulls
                && effort.equals(((SameEffortPredicate) other).effort)); // state check
    }
}
