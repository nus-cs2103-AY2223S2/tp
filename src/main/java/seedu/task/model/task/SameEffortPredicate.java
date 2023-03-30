package seedu.task.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Effort} matches input effort level.
 */
public class SameEffortPredicate implements Predicate<Task> {

    private Effort effort;
    public SameEffortPredicate(Effort effort) {
        this.effort = effort;
    }
    @Override
    public boolean test(Task task) {
        return task.getEffort().equals(this.effort);
    }
}
