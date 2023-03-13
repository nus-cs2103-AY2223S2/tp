package seedu.calidr.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import net.fortuna.ical4j.data.ParserException;
import seedu.calidr.exception.CalidrException;
import seedu.calidr.model.task.Event;
import seedu.calidr.model.task.Priority;
import seedu.calidr.model.task.ToDo;
import seedu.calidr.model.tasklist.TaskList;

public class CalendarStorageTest {
    @Test
    public void saveAndRetrieve() throws ParserException, IOException, CalidrException {
        var taskList = new TaskList();
        var storage = new CalendarStorage();
        var now = LocalDateTime.now();
        taskList.addTask(new Event("Event 1", now, now.plusHours(2)), "");
        taskList.addTask(new ToDo("ToDo 1", now, Priority.HIGH), "");

        var buffer = new ByteArrayOutputStream();
        storage.saveTaskList(taskList, buffer);
        var calStr = buffer.toString();

        var nTaskList = storage.readTaskList(new ByteArrayInputStream(calStr.getBytes()));
        var tasks = nTaskList.getTasks().collect(Collectors.toList());
        var ev = (Event) tasks.get(0);
        assertEquals("Event 1", ev.getDescription());
        assertEquals(now.truncatedTo(ChronoUnit.SECONDS), ev.getFrom().truncatedTo(ChronoUnit.SECONDS));
        assertEquals(now.plusHours(2).truncatedTo(ChronoUnit.SECONDS), ev.getTo().truncatedTo(ChronoUnit.SECONDS));

        var td = (ToDo) tasks.get(1);
        assertEquals("ToDo 1", td.getDescription());
        assertEquals(Priority.HIGH, td.getPriority());
        assertEquals(now.truncatedTo(ChronoUnit.SECONDS), td.getBy().truncatedTo(ChronoUnit.SECONDS));
    }
}
