package trackr.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void constructor_blankStatus_throwsIllegalArgumentException() {
        String blankTaskStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskStatus(blankTaskStatus));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidTaskStatus = "Z";
        assertThrows(IllegalArgumentException.class, () -> new TaskStatus(invalidTaskStatus));
    }

    @Test
    public void constructor_noArguments_success() {
        TaskStatus expectedStatus = new TaskStatus("N");
        assertEquals(expectedStatus, new TaskStatus());
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
        assertFalse(TaskStatus.isValidTaskStatus("M")); // invalid status
        assertFalse(TaskStatus.isValidTaskStatus("DN")); // both D and N
        assertFalse(TaskStatus.isValidTaskStatus("N N")); // more than one character
        assertFalse(TaskStatus.isValidTaskStatus("12345")); // numbers only
        assertFalse(TaskStatus.isValidTaskStatus("D ")); // white spaces at the back

        // valid task status
        assertTrue(TaskStatus.isValidTaskStatus("N")); //  not done
        assertTrue(TaskStatus.isValidTaskStatus("D")); // done
        assertTrue(TaskStatus.isValidTaskStatus("n")); // small letter
        assertTrue(TaskStatus.isValidTaskStatus("d")); // small letter
    }

    @Test
    public void toStringTest() {
        TaskStatus done = new TaskStatus("D");
        assertEquals("Done", done.toString());

        TaskStatus notDone = new TaskStatus("N");
        assertEquals("Not Done", notDone.toString());
    }

    @Test
    public void toJsonString() {
        TaskStatus done = new TaskStatus("D");
        assertEquals("D", done.toJsonString());

        TaskStatus notDone = new TaskStatus("N");
        assertEquals("N", notDone.toJsonString());
    }

    @Test
    public void equals() {
        TaskStatus done = new TaskStatus("D");
        TaskStatus notDone = new TaskStatus("N");

        assertTrue(done.equals(done)); //same object
        assertTrue(done.equals(new TaskStatus("d"))); //both are done

        assertFalse(done.equals(null)); //null
        assertFalse(done.equals(notDone)); //done vs not done
        assertFalse(done.equals("N")); //different types
    }

    @Test
    public void hashCodeTest() {
        TaskStatus done = new TaskStatus("d");
        TaskStatus notDone = new TaskStatus("n");

        // Hashcode 1231 in Java represents true while 1237 represents false
        assertEquals(1231, done.hashCode());
        assertEquals(1237, notDone.hashCode());
    }
}
