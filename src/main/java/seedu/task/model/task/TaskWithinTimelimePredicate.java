package seedu.task.model.task;

import java.time.Duration;
import java.util.function.Predicate;


/**
 * Tests that a {@code Task}'s {@code Deadline} or {@code to} matches any of the keywords given.
 */
public class TaskWithinTimelimePredicate implements Predicate<Task> {
    private final Duration timeframe;

    public TaskWithinTimelimePredicate(Duration timeframe) {
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
                || (other instanceof TaskWithinTimelimePredicate // instanceof handles nulls
                && timeframe.equals(((TaskWithinTimelimePredicate) other).timeframe)); // state check
    }

}
