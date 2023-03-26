package seedu.modtrek.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.logic.commands.CommandResult.DEFAULT_SORT;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", true, false, false,
                DEFAULT_SORT, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different isDisplayAllModules value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, DEFAULT_SORT,
                false)));

        // different isDisplayProgress value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, true, false, DEFAULT_SORT,
                false)));

        // different isDisplayFilteredModules value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, true, DEFAULT_SORT,
                false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, false, DEFAULT_SORT,
                true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different isDisplayAllModules value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, false, DEFAULT_SORT,
                false).hashCode());

        // different isDisplayProgress value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, true, false, DEFAULT_SORT,
                false).hashCode());

        // different isDisplayFilteredModules value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, true, DEFAULT_SORT,
                false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, false, DEFAULT_SORT,
                true).hashCode());
    }
}
