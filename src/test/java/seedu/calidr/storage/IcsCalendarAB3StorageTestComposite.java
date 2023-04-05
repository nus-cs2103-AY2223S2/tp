package seedu.calidr.storage;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import net.fortuna.ical4j.data.ParserException;
import seedu.calidr.exception.CalidrException;

public class IcsCalendarAB3StorageTestComposite {
    @Test
    public void saveAndRetrieve() throws ParserException, IOException, CalidrException {
//        var taskList = new TaskList();
//        var storage = new IcsCalendarStorage();
//        var now = LocalDateTime.now();
//        taskList.addTask(new Event(
//            new Title("Event 1"),
//            new EventDateTimes(now, now.plusHours(2)),
//            Priority.HIGH
//        ), "");
//        taskList.addTask(new ToDo(
//            new Title("ToDo 1"),
//            new TodoDateTime(now),
//            Priority.HIGH
//        ), "");
//
//        var buffer = new ByteArrayOutputStream();
//        storage.saveTaskList(taskList, buffer);
//        var calStr = buffer.toString();
//
//        var nTaskList = storage.readTaskList(new ByteArrayInputStream(calStr.getBytes()));
//        var tasks = nTaskList.getTasks().collect(Collectors.toList());
//        var ev = (Event) tasks.get(0);
//        assertEquals("Event 1", ev.getTitle());
//        assertEquals(now.truncatedTo(ChronoUnit.SECONDS), ev.getFrom().truncatedTo(ChronoUnit.SECONDS));
//        assertEquals(now.plusHours(2).truncatedTo(ChronoUnit.SECONDS), ev.getTo().truncatedTo(ChronoUnit.SECONDS));
//
//        var td = (ToDo) tasks.get(1);
//        assertEquals("ToDo 1", td.getTitle());
//        assertEquals(Priority.HIGH, td.getPriority());
//        assertEquals(now.truncatedTo(ChronoUnit.SECONDS), td.getBy().truncatedTo(ChronoUnit.SECONDS));
    }
}
