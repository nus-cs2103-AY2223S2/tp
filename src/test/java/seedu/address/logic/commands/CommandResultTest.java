package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {

    @Test
    public void constructor_allFields_success() {
        CommandResult result = new CommandResult("Feedback to user", true, true);
        assertEquals("Feedback to user", result.getFeedbackToUser());
        assertTrue(result.isShowHelp());
        assertTrue(result.isExit());
    }

    @Test
    public void constructor_feedbackToUser_success() {
        CommandResult result = new CommandResult("Feedback to user");
        assertEquals("Feedback to user", result.getFeedbackToUser());
        assertFalse(result.isShowHelp());
        assertFalse(result.isExit());
    }

    @Test
    public void equals_sameCommandResult_returnsTrue() {
        CommandResult result1 = new CommandResult("Feedback to user", true, true);
        CommandResult result2 = new CommandResult("Feedback to user", true, true);
        assertEquals(result1, result2);
    }

    @Test
    public void equals_differentFeedbackToUser_returnsFalse() {
        CommandResult result1 = new CommandResult("Feedback to user", true, true);
        CommandResult result2 = new CommandResult("Different feedback to user", true, true);
        assertNotEquals(result1, result2);
    }

    @Test
    public void equals_differentShowHelp_returnsFalse() {
        CommandResult result1 = new CommandResult("Feedback to user", true, true);
        CommandResult result2 = new CommandResult("Feedback to user", false, true);
        assertNotEquals(result1, result2);
    }

    @Test
    public void equals_differentExit_returnsFalse() {
        CommandResult result1 = new CommandResult("Feedback to user", true, true);
        CommandResult result2 = new CommandResult("Feedback to user", true, false);
        assertNotEquals(result1, result2);
    }

    @Test
    public void toString_returnsExpectedString() {
        CommandResult result = new CommandResult("Feedback to user", true, true, true);
        assertEquals("Command Result: Feedback to user, true, true, true", result.toString());
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
    }
}
