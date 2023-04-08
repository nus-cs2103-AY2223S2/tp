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
        assertThrows(NullPointerException.class, () -> TaskStatus.isValidStatus(null, TaskStatus.STATUSES));

        // invalid task status
        assertFalse(TaskStatus.isValidStatus("", TaskStatus.STATUSES)); // empty string
        assertFalse(TaskStatus.isValidStatus(" ", TaskStatus.STATUSES)); // spaces only
        assertFalse(TaskStatus.isValidStatus("^", TaskStatus.STATUSES)); // only non-alphanumeric characters
        assertFalse(TaskStatus.isValidStatus("N*", TaskStatus.STATUSES)); // contains non-alphanumeric characters
        assertFalse(TaskStatus.isValidStatus("M", TaskStatus.STATUSES)); // invalid status
        assertFalse(TaskStatus.isValidStatus("DN", TaskStatus.STATUSES)); // both D and N
        assertFalse(TaskStatus.isValidStatus("N N", TaskStatus.STATUSES)); // more than one character
        assertFalse(TaskStatus.isValidStatus("12345", TaskStatus.STATUSES)); // numbers only
        assertFalse(TaskStatus.isValidStatus("D ", TaskStatus.STATUSES)); // white spaces at the back

        // valid task status
        assertTrue(TaskStatus.isValidStatus("N", TaskStatus.STATUSES)); //  not done
        assertTrue(TaskStatus.isValidStatus("D", TaskStatus.STATUSES)); // done
        assertTrue(TaskStatus.isValidStatus("n", TaskStatus.STATUSES)); // small letter
        assertTrue(TaskStatus.isValidStatus("d", TaskStatus.STATUSES)); // small letter
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
    public void compare() {
        TaskStatus done = new TaskStatus("D");
        TaskStatus doneCopy = new TaskStatus("d");
        TaskStatus notDone = new TaskStatus("N");

        // same value -> 0
        assertEquals(0, done.compare(done));
        assertEquals(0, done.compare(doneCopy));

        // higher priority
        assertEquals(1, done.compare(notDone));
        assertEquals(-1, notDone.compare(done));
    }

}
