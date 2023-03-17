package seedu.calidr.commons.util;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import com.calendarfx.model.Interval;

import seedu.calidr.model.TaskEntry;
import seedu.calidr.model.task.Event;
import seedu.calidr.model.task.Task;
import seedu.calidr.model.task.ToDo;
import seedu.calidr.model.task.params.Priority;

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
            interval = new Interval(event.getEventDateTimes().from, event.getEventDateTimes().to);
        } else if (task instanceof ToDo) {
            ToDo toDo = (ToDo) task;
            LocalDateTime startDt = toDo.getBy().value;
            LocalDateTime endDt = startDt.withHour(23).withMinute(59).withSecond(59);
            if (endDt.isAfter(startDt.plusHours(1))) {
                endDt = startDt.plusHours(1);
            }
            interval = new Interval(startDt, endDt);
        } else {
            throw new IllegalArgumentException("Unknown task type");
            // TODO: Custom Exception
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
                task.getTitle(),
                tick,
                PRIORITY_SHORT.getOrDefault(task.getPriority(), "M")
        );
    }
}
