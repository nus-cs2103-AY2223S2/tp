package vimification.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testRobustConstructor() {
        Task task = new Task("GEX Essay", null, Status.NOT_DONE, Priority.NOT_URGENT);
        assertEquals(task.getTitle(), "GEX Essay");
        assertNull(task.getDeadline());
        assertEquals(task.getStatus(), Status.NOT_DONE);
        assertEquals(task.getPriority(), Priority.NOT_URGENT);
        assertTrue(task.getLabels().isEmpty());
    }

    @Test
    public void testSimpleConstructor() {
        Task task = new Task("GEX Essay");
        assertEquals(task.getTitle(), "GEX Essay");
        assertNull(task.getDeadline());
        assertEquals(task.getStatus(), Status.NOT_DONE);
        assertEquals(task.getPriority(), Priority.UNKNOWN);
        assertTrue(task.getLabels().isEmpty());
    }

    @Test
    public void testSetDeadline() {
        Task task = new Task("GEX Essay");
        LocalDateTime ldt = LocalDateTime.of(2023, 04, 11, 00, 00);
        task.setDeadline(ldt);
        assertEquals(task.getDeadline(), ldt);
    }

    @Test
    public void testDeleteDeadline() {
        Task task = new Task("Eat Vitamin C");
        task.setDeadline(LocalDateTime.of(2023, 04, 11, 00, 00));
        assertNotNull(task.getDeadline());
        task.deleteDeadline();
        assertNull(task.getDeadline());
    }

    @Test
    public void testGetDeadlineAsString() {
        Task task = new Task("GEX Essay");
        assertEquals(task.getDeadlineAsString(), "-");
        LocalDateTime ldt = LocalDateTime.of(2023, 04, 15, 12, 34);
        task.setDeadline(ldt);
        assertEquals(task.getDeadlineAsString(), "2023-04-15 12:34");
    }

    @Test
    public void testSetStatus() {
        Task task = new Task("GEX Essay");
        assertEquals(task.getStatus(), Status.NOT_DONE);
        task.setStatus(Status.IN_PROGRESS);
        assertEquals(task.getStatus(), Status.IN_PROGRESS);
    }

    @Test
    public void testHasStatus() {
        Task task = new Task("Buy milk");
        assertEquals(task.getStatus(), Status.NOT_DONE);
        assertTrue(task.hasStatus(Status.NOT_DONE));
        task.setStatus(Status.IN_PROGRESS);
        assertEquals(task.getStatus(), Status.IN_PROGRESS);
        assertTrue(task.hasStatus(Status.IN_PROGRESS));
    }

    @Test
    public void testSetPriority() {
        Task task = new Task("GEX Essay");
        assertEquals(task.getPriority(), Priority.UNKNOWN);
        task.setPriority(Priority.NOT_URGENT);
        assertEquals(task.getPriority(), Priority.NOT_URGENT);
    }

    @Test
    public void testHasPriority() {
        Task task = new Task("Feed my cat");
        assertEquals(task.getPriority(), Priority.UNKNOWN);
        assertTrue(task.hasPriority(Priority.UNKNOWN));
        task.setPriority(Priority.NOT_URGENT);
        assertEquals(task.getPriority(), Priority.NOT_URGENT);
        assertTrue(task.hasPriority(Priority.NOT_URGENT));
    }

    @Test
    public void testContainsLabel() {
        Task task = new Task("Buy detergent");
        assertFalse(task.containsLabel("food"));
        assertFalse(task.containsLabel("errands"));

        task.addLabel("food");
        task.addLabel("errands");
        task.removeLabel("food");
        assertFalse(task.containsLabel("food"));
        assertTrue(task.containsLabel("errands"));
    }

    @Test
    public void testAddLabel() {
        Task task = new Task("ES2660 Essay");
        task.addLabel("academic");
        task.addLabel("ES2660");
        assertTrue(task.containsLabel("academic"));
        assertTrue(task.containsLabel("ES2660"));

        Set<String> labels = task.getLabels();
        assertFalse(labels.isEmpty());
        assertTrue(labels.size() == 2);
        assertTrue(labels.contains("academic"));
        assertFalse(labels.contains("ES2660"));
        assertTrue(labels.contains("es2660"));
    }

    @Test
    public void testAddLabel_throwsException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task("GEX Essay");
            task.addLabel("academic");
            task.addLabel("academic");
        });
        assertEquals("Label already exists", ex.getMessage());
    }

    @Test
    public void testRemoveLabel() {
        Task task = new Task("GEX Essay");
        task.addLabel("academic");
        task.addLabel("GEX1001");
        assertTrue(task.containsLabel("academic"));
        assertTrue(task.containsLabel("GEX1001"));

        task.removeLabel("academic");
        assertFalse(task.containsLabel("academic"));
        assertTrue(task.containsLabel("GEX1001"));

        task.removeLabel("GEX1001");
        assertFalse(task.containsLabel("GEX1001"));
    }

    @Test
    public void testRemoveLabel_throwsException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task("GEX Essay");
            task.addLabel("academic");
            task.removeLabel("academic");
            task.removeLabel("academic");
        });
        assertEquals("Label does not exist", ex.getMessage());
    }

    @Test
    public void testDisplay() {
        Task task = new Task("GEX Essay");
        assertEquals(task.display(), "GEX Essay");

        task.setDeadline(LocalDateTime.of(2023, 04, 11, 23, 45));
        assertEquals(task.display(), "GEX Essay; by: 2023-04-11 23:45");
    }

    @Test
    public void testClone() {
        LocalDateTime ldt = LocalDateTime.of(2023, 04, 11, 00, 00);
        Task task =
                new Task("Do Pitch presentation slides", ldt, Status.IN_PROGRESS, Priority.URGENT);
        task.addLabel("academic");
        task.addLabel("CS2101");

        Task clonedTask = task.clone();
        assertEquals(clonedTask.getTitle(), task.getTitle());
        assertEquals(clonedTask.getDeadline(), task.getDeadline());
        assertEquals(clonedTask.getStatus(), task.getStatus());
        assertEquals(clonedTask.getPriority(), task.getPriority());
        assertEquals(clonedTask.getLabels(), task.getLabels());

        clonedTask.setTitle("Do Demo presentation slides");
        clonedTask.setDeadline(LocalDateTime.of(2023, 04, 12, 00, 00));
        clonedTask.setStatus(Status.COMPLETED);
        clonedTask.setPriority(Priority.NOT_URGENT);
        clonedTask.addLabel("CS2103T");
        assertNotEquals(clonedTask.getTitle(), task.getTitle());
        assertNotEquals(clonedTask.getDeadline(), task.getDeadline());
        assertNotEquals(clonedTask.getStatus(), task.getStatus());
        assertNotEquals(clonedTask.getPriority(), task.getPriority());
        assertNotEquals(clonedTask.getLabels(), task.getLabels());
        assertTrue(clonedTask != task);
    }

    @Test
    public void testContainsKeyword() {
        Task task = new Task("Buy chicken rice");
        assertTrue(task.containsKeyword("chicken"));
        assertTrue(task.containsKeyword("Buy"));
        assertTrue(task.containsKeyword(" "));
        assert !task.containsKeyword("buy"); // It is case sensitive.
    }

    @Test
    public void testIsDateAfter() {
        Task task = new Task("Rehearse for concert");
        assertNull(task.getDeadline());
        assert !task.deadlineIsAfter(LocalDateTime.now());
        task.setDeadline(LocalDateTime.of(2023, 04, 11, 00, 00));
        assertNotNull(task.getDeadline());
        assert task.deadlineIsAfter(LocalDateTime.of(2023, 04, 10, 00, 00));
        assert !task.deadlineIsAfter(LocalDateTime.of(2023, 04, 12, 00, 00));
    }

    @Test
    public void testIsDateBefore() {
        Task task = new Task("CS2102 project meeting");
        assertNull(task.getDeadline());
        assert !task.deadlineIsBefore(LocalDateTime.now());
        task.setDeadline(LocalDateTime.of(2023, 04, 11, 00, 00));
        assertNotNull(task.getDeadline());
        assert task.deadlineIsBefore(LocalDateTime.of(2023, 04, 12, 00, 00));
        assert !task.deadlineIsBefore(LocalDateTime.of(2023, 04, 10, 00, 00));
    }
}
