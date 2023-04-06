package fasttrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fasttrack.ui.ScreenType;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult(
            "feedback", ScreenType.EXPENSE_SCREEN);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult(
            "feedback", ScreenType.EXPENSE_SCREEN)));
        assertTrue(commandResult.equals(new CommandResult(
            "feedback", false, false, ScreenType.EXPENSE_SCREEN)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult(
            "different", ScreenType.EXPENSE_SCREEN)));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult(
            "feedback", true, false, ScreenType.EXPENSE_SCREEN)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult(
            "feedback", false, true, ScreenType.EXPENSE_SCREEN)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback", ScreenType.EXPENSE_SCREEN);

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback", ScreenType.EXPENSE_SCREEN).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different", ScreenType.EXPENSE_SCREEN).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(
            "feedback", true, false, ScreenType.EXPENSE_SCREEN).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(
            "feedback", false, true, ScreenType.EXPENSE_SCREEN).hashCode());
    }
}
