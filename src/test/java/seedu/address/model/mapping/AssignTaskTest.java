package seedu.address.model.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.shared.Id;

public class AssignTaskTest {

    @Test
    public void constructor_validIds_success() {
        Id personId = new Id();
        Id taskId = new Id();
        AssignTask assignTask = new AssignTask(personId, taskId);
        assertEquals(personId, assignTask.getPersonId());
        assertEquals(taskId, assignTask.getTaskId());
    }

    @Test
    public void constructor_nullIds_throwsNullPointerException() {
        Id personId = new Id();
        Id taskId = null;
        assertThrows(NullPointerException.class, () -> new AssignTask(personId, taskId));
    }

    @Test
    public void equals_sameAssignTask_returnsTrue() {
        Id personId = new Id();
        Id taskId = new Id();
        AssignTask assignTask1 = new AssignTask(personId, taskId);
        AssignTask assignTask2 = new AssignTask(personId, taskId);
        assertEquals(assignTask1, assignTask2);
    }

    @Test
    public void equals_differentAssignTask_returnsFalse() {
        Id personId1 = new Id();
        Id taskId1 = new Id();
        AssignTask assignTask1 = new AssignTask(personId1, taskId1);

        Id personId2 = new Id();
        Id taskId2 = new Id();
        AssignTask assignTask2 = new AssignTask(personId2, taskId2);

        assertNotEquals(assignTask1, assignTask2);
    }
}
