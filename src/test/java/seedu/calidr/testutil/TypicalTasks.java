package seedu.calidr.testutil;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import seedu.calidr.model.task.Event;
import seedu.calidr.model.task.ToDo;
import seedu.calidr.model.task.params.EventDateTimes;
import seedu.calidr.model.task.params.Priority;
import seedu.calidr.model.task.params.Title;
import seedu.calidr.model.task.params.TodoDateTime;

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
            new Title("CS2101 Quiz 2"),
            new TodoDateTime(DATE_TIME1),
            Priority.HIGH
    );

    public static final ToDo TODO2 = (
            (Supplier<ToDo>) () -> {
                ToDo temp = new ToDo(
                        new Title("CS2101 Quiz 3"),
                        new TodoDateTime(DATE_TIME2),
                        Priority.LOW
                );
                temp.setDone(true);
                return temp;
            }
    ).get();

    public static final Event EVENT1 = new Event(
            new Title("CS2103T Lecture 1"),
            new EventDateTimes(DATE_TIME1,
                    DATE_TIME2)
    );

    public static final Event EVENT2 = new Event(
            new Title("CS2103T Lecture 2"),
            new EventDateTimes(DATE_TIME2,
                    DATE_TIME3),
            Priority.HIGH
    );

    public static final Event EVENT3 = (
            (Supplier<Event>) () -> {
                Event temp = new Event(
                        new Title("CS2103T Lecture 3"),
                        new EventDateTimes(DATE_TIME1,
                                DATE_TIME3),
                        Priority.LOW
                );
                temp.setDone(true);
                return temp;
            }
    ).get();

}
