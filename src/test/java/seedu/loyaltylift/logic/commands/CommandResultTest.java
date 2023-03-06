package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, null)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, null)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, null)));
    }

    @Test
    public void getCustomerIndex() {
        Integer index = 1;
        CommandResult commandResult = new CommandResult("feedback", false, false, index);
        assertEquals(commandResult.getCustomerIndex(), index);

        CommandResult nullCommandResult = new CommandResult("feedback", false, false, null);
        assertNull(nullCommandResult.getCustomerIndex());
    }

    @Test
    public void isShowCustomerSelection() {
        CommandResult falseCommandResult = new CommandResult("feedback");
        assertFalse(falseCommandResult.isShowCustomerSelection());

        CommandResult secondFalseCommandResult = new CommandResult("feedback", true, true, null);
        assertFalse(secondFalseCommandResult.isShowCustomerSelection());

        CommandResult trueCommandResult = new CommandResult("feedback", true, true, 0);
        assertTrue(trueCommandResult.isShowCustomerSelection());
    }

    @Test
    public void showHelp() {
        CommandResult trueCommandResult = new CommandResult("feedback", true, false, null);
        assertTrue(trueCommandResult.isShowHelp());

        CommandResult falseCommandResult = new CommandResult("feedback", false, false, null);
        assertFalse(falseCommandResult.isShowHelp());
    }

    @Test
    public void exit() {
        CommandResult trueCommandResult = new CommandResult("feedback", false, true, null);
        assertTrue(trueCommandResult.isExit());

        CommandResult falseCommandResult = new CommandResult("feedback", false, false, null);
        assertFalse(falseCommandResult.isExit());
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, null).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, null).hashCode());
    }
}
