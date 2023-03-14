package seedu.task.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.task.model.TaskBook;
import seedu.task.model.task.Deadline;
import seedu.task.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalDeadlines {

    public static final Deadline RETURN_BOOK = new DeadlineBuilder().withName("Return Book")
            .withDescription("Return book description")
            .withTags("Reminder")
            .withDate("2023-01-01 1800")
            .withEffort(1)
            .build();

    public static final Deadline ASSIGNMENT = new DeadlineBuilder().withName("Assignment")
            .withDescription("Assignment description")
            .withTags("Important")
            .withDate("2023-01-01 1800")
            .withEffort(1)
            .build();

    public static final Deadline PROJECT = new DeadlineBuilder().withName("Project")
            .withDescription("Project description")
            .withTags("Important")
            .withDate("2023-06-01 1800")
            .withEffort(5)
            .build();


    private TypicalDeadlines() {} // prevents instantiation

    /**
     * Returns an {@code TaskBook} with all the typical tasks.
     */
    public static TaskBook getTypicalDeadlineBook() {
        TaskBook ab = new TaskBook();
        for (Task task : getTypicalDeadlines()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalDeadlines() {
        return new ArrayList<>(Arrays.asList(RETURN_BOOK, ASSIGNMENT, PROJECT));
    }
}
