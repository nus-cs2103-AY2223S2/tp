package vimification.model.task;

import org.junit.jupiter.api.Test;
import java.util.Set;
import java.time.LocalDateTime;

public class TaskTest {

    @Test
    void testRobustConstructor() {
        Task task = new Task("GEX Essay", null, Status.NOT_DONE, Priority.NOT_URGENT);
        assert task.getTitle().equals("GEX Essay");
        assert task.getDeadline() == null;
        assert task.getStatus().equals(Status.NOT_DONE);
        assert task.getPriority().equals(Priority.NOT_URGENT);
        assert task.getLabels().isEmpty();
    }

    @Test
    void testSimpleConstructor() {
        Task task = new Task("GEX Essay");
        assert task.getTitle().equals("GEX Essay");
        assert task.getDeadline() == null;
        assert task.getStatus().equals(Status.NOT_DONE);
        assert task.getPriority().equals(Priority.UNKNOWN);
        assert task.getLabels().isEmpty();
    }

    @Test
    void testSetDeadline() {
        Task task = new Task("GEX Essay");
        LocalDateTime ldt = LocalDateTime.of(2023, 04, 11, 00, 00);
        task.setDeadline(ldt);
        assert task.getDeadline().equals(ldt);
    }

    @Test
    void testDeleteDeadline() {
        Task task = new Task("Eat Vitamin C");
        task.setDeadline(LocalDateTime.of(2023, 04, 11, 00, 00));
        assert task.getDeadline() != null;
        task.deleteDeadline();
        assert task.getDeadline() == null;
    }

    @Test
    void testGetDeadlineAsString() {
        Task task = new Task("GEX Essay");
        assert task.getDeadlineAsString().equals("-");
        LocalDateTime ldt = LocalDateTime.of(2023, 04, 15, 12, 34);
        task.setDeadline(ldt);
        assert task.getDeadlineAsString().equals("2023-04-15 12:34");
    }

    @Test
    void testSetStatus() {
        Task task = new Task("GEX Essay");
        assert task.getStatus().equals(Status.NOT_DONE);
        task.setStatus(Status.IN_PROGRESS);
        assert task.getStatus().equals(Status.IN_PROGRESS);
    }

    @Test
    void testHasStatus() {
        Task task = new Task("Buy milk");
        assert task.getStatus().equals(Status.NOT_DONE);
        assert task.hasStatus(Status.NOT_DONE);
        task.setStatus(Status.IN_PROGRESS);
        assert task.getStatus().equals(Status.IN_PROGRESS);
        assert task.hasStatus(Status.IN_PROGRESS);
    }

    @Test
    void testSetPriority() {
        Task task = new Task("GEX Essay");
        assert task.getPriority().equals(Priority.UNKNOWN);
        task.setPriority(Priority.NOT_URGENT);
        assert task.getPriority().equals(Priority.NOT_URGENT);
    }

    @Test
    void testHasPriority() {
        Task task = new Task("Feed my cat");
        assert task.getPriority().equals(Priority.UNKNOWN);
        assert task.hasPriority(Priority.UNKNOWN);
        task.setPriority(Priority.NOT_URGENT);
        assert task.getPriority().equals(Priority.NOT_URGENT);
        assert task.hasPriority(Priority.NOT_URGENT);
    }

    @Test
    void testContainsLabel() {
        Task task = new Task("Buy detergent");
        assert !task.containsLabel("food");
        assert !task.containsLabel("errands");

        task.addLabel("food");
        task.addLabel("errands");
        task.removeLabel("food");
        assert !task.containsLabel("food");
        assert task.containsLabel("errands");
    }

    @Test
    void testAddLabel() {
        Task task = new Task("GEX Essay");
        task.addLabel("academic");
        task.addLabel("GEX1001");
        assert task.containsLabel("academic");
        assert task.containsLabel("GEX1001");

        Set<String> labels = task.getLabels();
        assert labels.size() == 2;
        assert labels.contains("academic");
        assert labels.contains("GEX1001");
    }

    @Test
    void testRemoveLabel() {
        Task task = new Task("GEX Essay");
        task.addLabel("academic");
        task.addLabel("GEX1001");
        assert task.containsLabel("academic");
        assert task.containsLabel("GEX1001");

        task.removeLabel("academic");
        assert !task.containsLabel("academic");
        assert task.containsLabel("GEX1001");

        task.removeLabel("GEX1001");
        assert !task.containsLabel("GEX1001");
    }

    @Test
    void testDisplay() {
        Task task = new Task("GEX Essay");
        assert task.display().equals("GEX Essay");

        task.setDeadline(LocalDateTime.of(2023, 04, 11, 23, 45));
        assert task.display().equals("GEX Essay; by: 2023-04-11 23:45");
    }

    @Test
    void testClone() {
        LocalDateTime ldt = LocalDateTime.of(2023, 04, 11, 00, 00);
        Task task =
                new Task("Do Pitch presentation slides", ldt, Status.IN_PROGRESS, Priority.URGENT);
        task.addLabel("academic");
        task.addLabel("CS2101");

        Task clonedTask = task.clone();
        assert clonedTask.getTitle().equals(task.getTitle());
        assert clonedTask.getDeadline().equals(task.getDeadline());
        assert clonedTask.getStatus().equals(task.getStatus());
        assert clonedTask.getPriority().equals(task.getPriority());
        assert clonedTask.getLabels().equals(task.getLabels());

        clonedTask.setTitle("Do Demo presentation slides");
        clonedTask.setDeadline(LocalDateTime.of(2023, 04, 12, 00, 00));
        clonedTask.setStatus(Status.COMPLETED);
        clonedTask.setPriority(Priority.NOT_URGENT);
        clonedTask.addLabel("CS2103T");
        assert !clonedTask.getTitle().equals(task.getTitle());
        assert !clonedTask.getDeadline().equals(task.getDeadline());
        assert !clonedTask.getStatus().equals(task.getStatus());
        assert !clonedTask.getPriority().equals(task.getPriority());
        assert !clonedTask.getLabels().equals(task.getLabels());
        assert clonedTask != task;
    }

    @Test
    void testContainsKeyword() {
        Task task = new Task("Buy chicken rice");
        assert task.containsKeyword("chicken");
        assert task.containsKeyword("Buy");
        assert task.containsKeyword(" ");
        assert !task.containsKeyword("buy"); // It is case sensitive.
    }

    @Test
    void testIsDateAfter() {
        Task task = new Task("Rehearse for concert");
        assert task.getDeadline() == null;
        assert !task.deadlineIsAfter(LocalDateTime.now());
        task.setDeadline(LocalDateTime.of(2023, 04, 11, 00, 00));
        assert task.getDeadline() != null;
        assert task.deadlineIsAfter(LocalDateTime.of(2023, 04, 10, 00, 00));
        assert !task.deadlineIsAfter(LocalDateTime.of(2023, 04, 12, 00, 00));
    }

    @Test
    void testIsDateBefore() {
        Task task = new Task("CS2102 project meeting");
        assert task.getDeadline() == null;
        assert !task.deadlineIsBefore(LocalDateTime.now());
        task.setDeadline(LocalDateTime.of(2023, 04, 11, 00, 00));
        assert task.getDeadline() != null;
        assert task.deadlineIsBefore(LocalDateTime.of(2023, 04, 12, 00, 00));
        assert !task.deadlineIsBefore(LocalDateTime.of(2023, 04, 10, 00, 00));
    }
}
