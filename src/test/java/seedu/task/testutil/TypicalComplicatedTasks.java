package seedu.task.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.task.model.TaskBook;
import seedu.task.model.task.Deadline;
import seedu.task.model.task.Event;
import seedu.task.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalComplicatedTasks {

    public static final Deadline RETURN_BOOK = new DeadlineBuilder().withName("Return Book")
            .withDescription("Return book description")
            .withTags("Reminder")
            .withDate("2023-01-05 2000")
            .withAlertWindow("24").build();

    public static final Deadline ASSIGNMENT = new DeadlineBuilder().withName("Assignment")
            .withDescription("Assignment description")
            .withTags("Important")
            .withDate("2023-01-03 1800")
            .withAlertWindow("24").build();

    public static final Event REST = new EventBuilder().withName("REST")
            .withDescription("rest description")
            .withTags("Important")
            .withFrom("2023-01-03 1800")
            .withTo("2023-01-03 2000")
            .withAlertWindow("24").build();


    private TypicalComplicatedTasks() {} // prevents instantiation

    /**
     * Returns an {@code TaskBook} with all the typical tasks.
     */
    public static TaskBook getTypicalComplicatedTasks() {
        TaskBook ab = new TaskBook();
        for (Task task : getComplicatedTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getComplicatedTasks() {
        return new ArrayList<>(Arrays.asList(RETURN_BOOK, ASSIGNMENT, REST));
    }
}
