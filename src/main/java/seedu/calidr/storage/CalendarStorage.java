package seedu.calidr.storage;

import static net.fortuna.ical4j.model.Property.DESCRIPTION;
import static net.fortuna.ical4j.model.Property.LOCATION;
import static net.fortuna.ical4j.model.Property.CATEGORIES;
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
import net.fortuna.ical4j.model.TextList;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VToDo;
import net.fortuna.ical4j.model.property.DateProperty;
import net.fortuna.ical4j.model.property.Status;
import net.fortuna.ical4j.model.property.Categories;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Location;
import seedu.calidr.model.task.Event;
import seedu.calidr.model.task.Task;
import seedu.calidr.model.task.ToDo;
import seedu.calidr.model.task.params.Priority;
import seedu.calidr.model.task.params.Title;
import seedu.calidr.model.task.params.EventDateTimes;
import seedu.calidr.model.task.params.TodoDateTime;
import seedu.calidr.model.task.params.Tag;
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
        taskList.getTaskList().stream()
            .map(this::taskToComponent)
            .forEach(cal::add);

        new CalendarOutputter().output(cal, stream);
    }

    /**
     * Read the task list from the stream
     * @return Parsed task list
     * @throws ParserException If invalid format for ics file stream
     * @throws IOException Thrown while reading the ics file
     */
    public TaskList readTaskList(InputStream stream) throws ParserException, IOException {
        var cal = new CalendarBuilder().build(stream);

        var tasks = cal.getComponents().stream()
            .flatMap(c -> componentToTask(c).stream())
            .collect(Collectors.toList());
        var taskList = new TaskList();
        taskList.setTasks(tasks);
        return taskList;
    }

    private CalendarComponent taskToComponent(Task task) {
        CalendarComponent component;
        if (task instanceof Event) {
            var event = (Event) task;

            component = new VEvent(
                event.getEventDateTimes().from,
                event.getEventDateTimes().to,
                event.getTitle().value);

        } else if (task instanceof ToDo) {
            var todo = (ToDo) task;

            var vtodo = new VToDo(todo.getBy().value, todo.getTitle().value);

            vtodo.add(new net.fortuna.ical4j.model.property.Priority(convertPriority(todo.getPriority())));

            if (todo.isDone()) {
                vtodo.add(new Status(Status.VALUE_COMPLETED));
            }

            component = vtodo;
        }
        else {
            throw new UnsupportedOperationException();
        }

        final CalendarComponent componentRef = component;

        task.getDescription().ifPresent(desc -> componentRef.add(new Description(desc.value)));

        task.getLocation().ifPresent(location -> componentRef.add(new Location(location.value)));

        var tags = task.getTags().stream().map(tag -> tag.tagName).toArray(String[]::new);
        if (tags.length != 0) {
            componentRef.add(new Categories(new TextList(tags)));
        }

        return component;
    }

    private Optional<Task> componentToTask(CalendarComponent component) {
        Optional<Task> task = Optional.empty();
        if (component instanceof VEvent) {
            var vevent = (VEvent) component;
            var dtend = vevent.getEndDate().map(this::convertDateField);
            var dtstart = vevent.getStartDate().map(this::convertDateField);
            var summary = vevent.getSummary();

            var event =
                dtend.flatMap(dtEnd ->
                dtstart.flatMap(dtStart ->
                summary.map(summ -> new Event(
                    new Title(summ.getValue()),
                    new EventDateTimes(
                        LocalDateTime.from(dtStart),
                        LocalDateTime.from(dtEnd)
                    ))
                )));

            task = event.map(id -> id);
        } else if (component instanceof VToDo) {
            var vtodo = (VToDo) component;
            var summary = vtodo.getSummary();
            var isDone = vtodo.getStatus();
            var priority = vtodo.getPriority().map(this::convertPriorityField);
            var by = vtodo.getStartDate().map(this::convertDateField);

            var todo =
                by.flatMap(byd ->
                summary.map(summ -> new ToDo(
                    new Title(summ.getValue()),
                    new TodoDateTime(byd))
                ));

            todo.ifPresent(t ->
                t.setDone(isDone
                    .filter(c -> Status.VALUE_COMPLETED.equals(c.getValue()))
                    .isPresent())
            );

            todo.ifPresent(e ->
                e.setPriority(priority.orElse(Priority.MEDIUM))
            );

            task = todo.map(id -> id);
        }

        Optional<Description> description = component.getProperty(DESCRIPTION);
        Optional<Location> location = component.getProperty(LOCATION);
        Optional<Categories> categories = component.getProperty(CATEGORIES);

        task.ifPresent(t -> {
            description.ifPresent(desc -> t.setDescription(new seedu.calidr.model.task.params.Description(desc.getValue())));
            location.ifPresent(loc -> t.setLocation(new seedu.calidr.model.task.params.Location(loc.getValue())));
            var otags = categories.map(cats -> cats.getCategories().stream().map(Tag::new).collect(Collectors.toSet()));
            otags.ifPresent(t::setTags);
        });

        return task;
    }

    private LocalDateTime convertDateField(DateProperty<?> field) {
        return LocalDateTime.from(field.getDate());
    }

    private Priority convertPriorityField(net.fortuna.ical4j.model.property.Priority priority) {
        int prio = priority.getLevel();
        switch (prio) {
        case VALUE_HIGH:
            return Priority.HIGH;
        case VALUE_MEDIUM:
            return Priority.MEDIUM;
        case VALUE_LOW:
            return Priority.LOW;
        default:
            return Priority.MEDIUM;
        }
    }

    private int convertPriority(Priority prio) {
        switch (prio) {
        case HIGH:
            return VALUE_HIGH;
        case MEDIUM:
            return VALUE_MEDIUM;
        case LOW:
            return VALUE_LOW;
        default:
            return VALUE_MEDIUM;
        }
    }
}
