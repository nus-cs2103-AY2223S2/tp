package seedu.calidr.storage;

import static net.fortuna.ical4j.model.property.Priority.VALUE_HIGH;
import static net.fortuna.ical4j.model.property.Priority.VALUE_LOW;
import static net.fortuna.ical4j.model.property.Priority.VALUE_MEDIUM;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VToDo;
import net.fortuna.ical4j.model.property.Status;
import seedu.calidr.model.task.Event;
import seedu.calidr.model.task.Priority;
import seedu.calidr.model.task.Task;
import seedu.calidr.model.task.ToDo;
import seedu.calidr.model.tasklist.TaskList;

/**
 * Abstract representation of .ics file storage
 */
public class CalendarStorage {

    /**
     * Save the TaskList to a stream
     * @param taskList Task List
     * @param stream Output Stream
     * @throws IOException Thrown while writing to the output stream
     */
    public void saveTaskList(TaskList taskList, OutputStream stream) throws IOException {
        var cal = new Calendar();
        taskList.getTasks()
            .map(this::taskToComponent)
            .forEach(cal::add);

        new CalendarOutputter().output(cal, stream);
    }

    /**
     * Read the task list from the stream
     * @param stream
     * @return Parsed task list
     * @throws ParserException If invalid format for ics file stream
     * @throws IOException Thrown while reading the ics file
     */
    public TaskList readTaskList(InputStream stream) throws ParserException, IOException {
        var cal = new CalendarBuilder().build(stream);

        var tasks = cal.getComponents().stream()
            .flatMap(c -> componentToTask(c).stream())
            .collect(Collectors.toList());
        return new TaskList(tasks);
    }

    private CalendarComponent taskToComponent(Task t) {
        if (t instanceof Event) {
            var event = (Event) t;

            var vevent = new VEvent(
                event.getFrom(),
                event.getTo(),
                event.getDescription());
            return vevent;
        } else if (t instanceof ToDo) {
            var todo = (ToDo) t;

            var vtodo = new VToDo(todo.getBy(), todo.getDescription());

            vtodo.add(new net.fortuna.ical4j.model.property.Priority(appToIcsPriority(todo.getPriority())));

            if (todo.isDone()) {
                vtodo.add(new Status(Status.VALUE_COMPLETED));
            }
            return vtodo;
        }
        throw new RuntimeException(""); // TODO
    }

    private Optional<Task> componentToTask(CalendarComponent component) {
        if (component instanceof VEvent) {
            var vevent = (VEvent) component;
            var dtend = vevent.getEndDate();
            var dtstart = vevent.getStartDate();
            var summary = vevent.getSummary();

            return
                dtend.flatMap(dtEnd ->
                dtstart.flatMap(dtStart ->
                summary.map(value -> new Event(
                    value.getValue(),
                    LocalDateTime.from(dtStart.getDate()),
                    LocalDateTime.from(dtEnd.getDate()))
                )));
        } else if (component instanceof VToDo) {
            var vtodo = (VToDo) component;
            var summary = vtodo.getSummary();
            var isDone = vtodo.getStatus();
            var priority = vtodo.getPriority();
            var by = vtodo.getStartDate();

            if (!summary.isPresent() || !by.isPresent()) {
                return Optional.empty();
            }

            var todo = new ToDo(
                summary.get().getValue(),
                LocalDateTime.from(by.get().getDate())
            );
            todo.setDone(isDone
                .filter(
                    c -> Status.VALUE_COMPLETED.equals(c.getValue()))
                .isPresent());
            priority.flatMap(p -> icsToAppPriority(p.getLevel()))
                .ifPresent(todo::setPriority);
            return Optional.of(todo);
        }
        return Optional.empty();
    }

    private Optional<Priority> icsToAppPriority(int prio) {
        switch (prio) {
        case VALUE_HIGH:
            return Optional.of(Priority.HIGH);
        case VALUE_MEDIUM:
            return Optional.of(Priority.MEDIUM);
        case VALUE_LOW:
            return Optional.of(Priority.LOW);
        default:
            return Optional.empty();
        }
    }

    private int appToIcsPriority(Priority prio) {
        switch (prio) {
        case HIGH:
            return VALUE_HIGH;
        case MEDIUM:
            return VALUE_MEDIUM;
        case LOW:
            return VALUE_LOW;
        default:
            throw new RuntimeException(); // TODO
        }
    }
}
