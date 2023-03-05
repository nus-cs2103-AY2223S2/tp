package seedu.calidr.testutil;

import java.time.LocalDateTime;

import seedu.calidr.model.task.Deadline;
import seedu.calidr.model.task.Event;
import seedu.calidr.model.task.Priority;
import seedu.calidr.model.task.ToDo;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final ToDo TODO1 = new ToDo("CS2103T Quiz 1");
    public static final ToDo TODO2 = new ToDo("CS2103T Quiz 2", Priority.HIGH);
    public static final ToDo TODO3 = new ToDo("CS2103T Quiz 3", Priority.LOW);

    public static final Deadline DEADLINE1 = new Deadline("CS2101 Quiz 1", LocalDateTime.of(2019, 10, 10, 10, 10));

    public static final Deadline DEADLINE2 = new Deadline("CS2101 Quiz 2", LocalDateTime.of(2019, 10, 10, 10, 10), Priority.HIGH);

    public static final Deadline DEADLINE3 = new Deadline("CS2101 Quiz 3", LocalDateTime.of(2019, 12, 10, 10, 10), Priority.LOW);

    public static final Event EVENT1 = new Event("CS2103T Lecture 1", LocalDateTime.of(2019, 10, 10, 10, 10), LocalDateTime.of(2019, 10, 10, 11, 10));

    public static final Event EVENT2 = new Event("CS2103T Lecture 2", LocalDateTime.of(2019, 10, 10, 10, 10), LocalDateTime.of(2019, 10, 10, 11, 10), Priority.HIGH);

    public static final Event EVENT3 = new Event("CS2103T Lecture 3", LocalDateTime.of(2020, 10, 10, 10, 10), LocalDateTime.of(2020, 10, 10, 11, 10), Priority.LOW);

}
