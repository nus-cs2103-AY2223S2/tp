package trackr.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidTaskName = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskName(invalidTaskName));
    }

    @Test
    public void isValidTaskName() {
        // null task name
        assertThrows(NullPointerException.class, () -> TaskName.isValidTaskName(null));

        // invalid task name
        assertFalse(TaskName.isValidTaskName("")); // empty string
        assertFalse(TaskName.isValidTaskName(" ")); // spaces only
        assertFalse(TaskName.isValidTaskName("^")); // only non-alphanumeric characters
        assertFalse(TaskName.isValidTaskName("bake*")); // contains non-alphanumeric characters

        // valid task name
        assertTrue(TaskName.isValidTaskName("sort inventory")); // alphabets only
        assertTrue(TaskName.isValidTaskName("12345")); // numbers only
        assertTrue(TaskName.isValidTaskName("buy 100kg of flour")); // alphanumeric characters
        assertTrue(TaskName.isValidTaskName("Buy Eggs")); // with capital letters
        assertTrue(TaskName.isValidTaskName("Buy 10kg of Flour and 5kg of Eggs and 2kg of sugar")); // long names
    }
}
