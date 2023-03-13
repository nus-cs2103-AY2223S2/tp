package seedu.calidr.storage;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VToDo;
import org.junit.jupiter.api.Test;
import seedu.calidr.exception.CalidrException;
import seedu.calidr.model.task.Event;
import seedu.calidr.model.task.Priority;
import seedu.calidr.model.task.ToDo;
import seedu.calidr.model.tasklist.TaskList;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerializationTest {
    @Test
    public void Test() throws ParserException, IOException, CalidrException {
        var taskList = new TaskList();
        var storage = new CalendarStorage();
        var now = LocalDateTime.now();
        taskList.addTask(new Event("Event 1", now, now.plusHours(2)), "");
        taskList.addTask(new ToDo("ToDo 1", Priority.HIGH), "");

        var buffer = new ByteArrayOutputStream();
        storage.saveTaskList(taskList, buffer);
        var calStr = buffer.toString();

        var nTaskList = storage.readTaskList(new StringReader(calStr));
        var tasks = nTaskList.getTasks().collect(Collectors.toList());
        var ev = (Event)tasks.get(0);
        assertEquals("Event 1", ev.getDescription());
        assertEquals(now.truncatedTo(ChronoUnit.SECONDS), ev.getFrom().truncatedTo(ChronoUnit.SECONDS));
        assertEquals(now.plusHours(2).truncatedTo(ChronoUnit.SECONDS), ev.getTo().truncatedTo(ChronoUnit.SECONDS));

        var td = (ToDo)tasks.get(1);
        assertEquals("ToDo 1", td.getDescription());
        assertEquals(Priority.HIGH, td.getPriority());
    }
}
