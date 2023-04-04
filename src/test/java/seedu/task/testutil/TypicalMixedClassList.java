package seedu.task.testutil;

import java.util.List;

import seedu.task.model.task.Task;

/**
 * Generates a list of tasks for testing.
 */
public class TypicalMixedClassList {

    public static List<Task> getTypicalMixedClassList() {
        List<Task> list = TypicalTasks.getTypicalTasks();
        list.add(TypicalDeadlines.CHICKEN_EXPIRY);
        list.add(TypicalDeadlines.SUSHI_EXPIRY);
        list.add(TypicalEvents.EXAM);
        list.add(TypicalEvents.SLEEPOVER);
        return list;
    }
}
