package seedu.calidr.testutil;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import seedu.calidr.model.task.Event;
import seedu.calidr.model.task.Priority;
import seedu.calidr.model.task.ToDo;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final LocalDateTime DATE_TIME1 =
            LocalDateTime.of(2023, 3,
                    10, 10, 10);
    public static final LocalDateTime DATE_TIME2 =
            LocalDateTime.of(2023, 3,
                    11, 10, 10);

    public static final LocalDateTime DATE_TIME3 =
            LocalDateTime.of(2023, 3,
                    12, 10, 10);
    public static final ToDo TODO1 = new ToDo(
            "CS2101 Quiz 2",
            DATE_TIME1,
            Priority.HIGH
    );

    public static final ToDo TODO2 = (
            (Supplier<ToDo>) () -> {
                ToDo temp = new ToDo(
                        "CS2101 Quiz 3",
                        DATE_TIME2,
                        Priority.LOW
                );
                temp.setDone(true);
                return temp;
            }
    ).get();

    public static final Event EVENT1 = new Event(
            "CS2103T Lecture 1",
            DATE_TIME1,
            DATE_TIME2
    );

    public static final Event EVENT2 = new Event(
            "CS2103T Lecture 2",
            DATE_TIME2,
            DATE_TIME3,
            Priority.HIGH
    );

    public static final Event EVENT3 = (
            (Supplier<Event>) () -> {
                Event temp = new Event(
                        "CS2103T Lecture 3",
                        DATE_TIME1,
                        DATE_TIME3,
                        Priority.LOW
                );
                temp.setDone(true);
                return temp;
            }
    ).get();

}
