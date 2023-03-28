package arb.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.model.ListType;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback", ListType.NONE);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback", ListType.NONE)));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false, ListType.NONE)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different", ListType.NONE)));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, false, ListType.NONE)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, false, ListType.NONE)));

        // different enter link mode value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, true, ListType.NONE)));

        // different list type value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", ListType.CLIENT)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback", ListType.NONE);

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback", ListType.NONE).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different", ListType.NONE).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false,
                false, ListType.NONE).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true,
                false, ListType.NONE).hashCode());

        // different enter link mode value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false,
                true, ListType.NONE));

        // different list type value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", ListType.CLIENT).hashCode());
    }
}
