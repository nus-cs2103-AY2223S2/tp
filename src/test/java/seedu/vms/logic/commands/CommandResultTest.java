package seedu.vms.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.CommandMessage;


public class CommandResultTest {
    @Test
    public void equals() {
        CommandMessage commandResult = new CommandMessage("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandMessage("feedback")));
        assertTrue(commandResult.equals(new CommandMessage("feedback", false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandMessage("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandMessage("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandMessage("feedback", false, true)));
    }

    @Test
    public void hashcode() {
        CommandMessage commandResult = new CommandMessage("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandMessage("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandMessage("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandMessage("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandMessage("feedback", false, true).hashCode());
    }
}
