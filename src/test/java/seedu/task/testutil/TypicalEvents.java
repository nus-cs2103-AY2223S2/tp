package seedu.task.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.task.model.TaskBook;
import seedu.task.model.task.Event;
import seedu.task.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event MEETING = new EventBuilder().withName("Meeting")
            .withDescription("Meeting description")
            .withTags("Reminder")
            .withFrom("2023-01-01 1800")
            .withTo("2023-01-02 1800")
            .withEffort(5)
            .build();

    public static final Event STUDY = new EventBuilder().withName("Study")
            .withDescription("Study description")
            .withTags("Concentrate")
            .withFrom("2023-01-02 1800")
            .withTo("2023-01-03 1800")
            .withEffort(3)
            .build();

    public static final Event REST = new EventBuilder().withName("REST")
            .withDescription("rest description")
            .withTags("Important")
            .withFrom("2023-01-03 1800")
            .withTo("2023-01-03 2000")
            .withEffort(2)
            .build();


    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code TaskBook} with all the typical tasks.
     */
    public static TaskBook getTypicalEventBook() {
        TaskBook ab = new TaskBook();
        for (Task task : getTypicalEvents()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(MEETING, STUDY, REST));
    }
}
