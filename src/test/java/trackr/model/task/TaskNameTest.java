package trackr.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskNameTest {

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidTaskName = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskName(invalidTaskName));
    }
    //@@author

    //@@author liumc-sg-reused
    @Test
    public void isValidTaskName() {
        // null task name
        assertThrows(NullPointerException.class, () -> TaskName.isValidName(null));

        // invalid task name
        assertFalse(TaskName.isValidName("")); // empty string
        assertFalse(TaskName.isValidName(" ")); // spaces only
        assertFalse(TaskName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(TaskName.isValidName("bake*")); // contains non-alphanumeric characters

        // valid task name
        assertTrue(TaskName.isValidName("sort inventory")); // alphabets only
        assertTrue(TaskName.isValidName("12345")); // numbers only
        assertTrue(TaskName.isValidName("buy 100kg of flour")); // alphanumeric characters
        assertTrue(TaskName.isValidName("Buy Eggs")); // with capital letters
        assertTrue(TaskName.isValidName("Buy 10kg of Flour and 5kg of Eggs and 2kg of sugar")); // long names
    }
    //@@author

    @Test
    public void toStringTest() {
        String expectedTaskName = "Sort Inventory";
        assertEquals(expectedTaskName, new TaskName("Sort Inventory").toString());
    }

    @Test
    public void equals() {
        TaskName taskName = new TaskName("Sort Inventory");
        TaskName differentTaskName = new TaskName("Sort");

        assertTrue(taskName.equals(taskName)); //same object
        assertTrue(taskName.equals(new TaskName("Sort Inventory"))); //same task name

        assertFalse(taskName.equals(null)); //null
        assertFalse(taskName.equals(differentTaskName)); //different task name
        assertFalse(taskName.equals(1)); //different type
    }
}
