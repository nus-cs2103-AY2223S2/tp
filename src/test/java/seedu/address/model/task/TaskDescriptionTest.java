package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDescription(null));
    }

    @Test
    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskDescription(invalidName));
    }

    @Test
    public void isValidTaskDescription() {
        // null name
        assertThrows(NullPointerException.class, () -> TaskDescription.isValidTaskDescription(null));

        // invalid name
        assertFalse(TaskDescription.isValidTaskDescription("")); // empty string
        assertFalse(TaskDescription.isValidTaskDescription(" ")); // spaces only
        assertFalse(TaskDescription.isValidTaskDescription("^")); // only non-alphanumeric characters
        assertFalse(TaskDescription.isValidTaskDescription("eat food*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TaskDescription.isValidTaskDescription("eat salmon")); // alphabets only
        assertTrue(TaskDescription.isValidTaskDescription("12345")); // numbers only
        assertTrue(TaskDescription.isValidTaskDescription("eat 2 times")); // alphanumeric characters
        assertTrue(TaskDescription.isValidTaskDescription("Eat FooD")); // with capital letters
        assertTrue(TaskDescription.isValidTaskDescription("Eat Salmon And Turtles And Tigers")); // long description
    }
}
