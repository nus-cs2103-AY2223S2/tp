package trackr.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskStatus(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidTaskStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskStatus(invalidTaskStatus));
    }

    @Test
    public void isValidTaskStatus() {
        // null task status
        assertThrows(NullPointerException.class, () -> TaskStatus.isValidTaskStatus(null));

        // invalid task status
        assertFalse(TaskStatus.isValidTaskStatus("")); // empty string
        assertFalse(TaskStatus.isValidTaskStatus(" ")); // spaces only
        assertFalse(TaskStatus.isValidTaskStatus("^")); // only non-alphanumeric characters
        assertFalse(TaskStatus.isValidTaskStatus("N*")); // contains non-alphanumeric characters
        assertFalse(TaskStatus.isValidTaskStatus("DN")); // both D and N
        assertFalse(TaskStatus.isValidTaskStatus("12345")); // numbers only
        assertFalse(TaskStatus.isValidTaskStatus("D ")); // white spaces at the back

        // valid task status
        assertTrue(TaskStatus.isValidTaskStatus("N")); //  not done
        assertTrue(TaskStatus.isValidTaskStatus("D")); // done
        assertTrue(TaskStatus.isValidTaskStatus("n")); // small letter
        assertTrue(TaskStatus.isValidTaskStatus("d")); // small letter
    }
}
