package seedu.calidr.commons.util;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import com.calendarfx.model.Interval;

import seedu.calidr.model.TaskEntry;
import seedu.calidr.model.task.Event;
import seedu.calidr.model.task.Priority;
import seedu.calidr.model.task.Task;
import seedu.calidr.model.task.ToDo;

/**
 * Task to TaskEntry converter.
 */
public final class TaskEntryUtil {

    private static final Map<Priority, String> PRIORITY_SHORT = Map.of(
            Priority.LOW, "L",
            Priority.MEDIUM, "M",
            Priority.HIGH, "H"
    );

    /**
     * Converts the Calidr Task object to a CalendarFX TaskEntry object.
     *
     * @param task the task
     * @return the task entry
     */
    public static TaskEntry convert(Task task) {
        Objects.requireNonNull(task);
        TaskEntry taskEntry = new TaskEntry();

        Interval interval;
        if (task instanceof Event) {
            Event event = (Event) task;
            interval = new Interval(event.getFrom(), event.getTo());
        } else if (task instanceof ToDo) {
            ToDo toDo = (ToDo) task;
            LocalDateTime etdt = toDo.getBy();
            interval = new Interval(etdt, etdt.plusHours(1));
        } else {
            interval = new Interval();
        }

        taskEntry.setTitle(toCustomString(task));
        taskEntry.setInterval(interval);
        taskEntry.setPriority(task.getPriority());
        taskEntry.setIsDone(task.isDone());
        taskEntry.setUserObject(task);

        return taskEntry;
    }

    private static String toCustomString(Task task) {
        String tick = task.isDone() ? "✓" : "  ";
        return String.format("%s\t[%s] {%s}",
                task.getDescription(),
                tick,
                PRIORITY_SHORT.getOrDefault(task.getPriority(), "M")
        );
    }
}
