package seedu.task.model.task;

import java.time.Duration;
import java.util.function.Predicate;


/**
 * Tests that a {@code Task}'s {@code Deadline} or {@code to} matches any of the keywords given.
 */
public class TaskWithinTimelinePredicate implements Predicate<Task> {
    private final Duration timeframe;

    public TaskWithinTimelinePredicate(Duration timeframe) {
        this.timeframe = timeframe;
    }

    @Override
    public boolean test(Task task) {
        task.setAlertWindow(timeframe);
        return task.isComingUp();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskWithinTimelinePredicate // instanceof handles nulls
                && timeframe.equals(((TaskWithinTimelinePredicate) other).timeframe)); // state check
    }

}
