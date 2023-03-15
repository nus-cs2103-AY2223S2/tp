package seedu.task.model.task;
import java.util.function.Predicate;


/**
 * Tests that a {@code Task}'s {@code Deadline} or {@code to} matches any of the keywords given.
 */
public class StartupTaskWithinTimelinePredicate implements Predicate<Task> {

    public StartupTaskWithinTimelinePredicate() {
    }

    @Override
    public boolean test(Task task) {
        return task.isComingUp();
    }

    @Override
    public boolean equals(Object other) {
        return other == this; // short circuit if same object
    }

}
