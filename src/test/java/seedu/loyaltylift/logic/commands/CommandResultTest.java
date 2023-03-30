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
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));

        // different showCustomerSelection value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, true, false)));

        // different showOrderSelection value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, false, true)));

        // different customer index -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, 0, null)));

        // different order index -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, null, 0)));
    }

    @Test
    public void getCustomerIndex() {
        Integer index = 1;
        CommandResult commandResult = new CommandResult("feedback", false, false, index, null);
        assertEquals(commandResult.getCustomerIndex(), index);

        CommandResult nullCommandResult = new CommandResult("feedback", false, false);
        assertNull(nullCommandResult.getCustomerIndex());

        CommandResult alsoNullCommandResult = new CommandResult("feedback");
        assertNull(alsoNullCommandResult.getCustomerIndex());
    }

    @Test
    public void getOrderIndex() {
        Integer index = 1;
        CommandResult commandResult = new CommandResult("feedback", false, false, null, index);
        assertEquals(commandResult.getOrderIndex(), index);

        CommandResult nullCommandResult = new CommandResult("feedback", false, false, null, null);
        assertNull(nullCommandResult.getOrderIndex());

        CommandResult alsoNullCommandResult = new CommandResult("feedback");
        assertNull(alsoNullCommandResult.getOrderIndex());
    }

    @Test
    public void isShowCustomerSelection() {
        CommandResult falseCommandResult = new CommandResult("feedback");
        assertFalse(falseCommandResult.isShowCustomerSelection());

        falseCommandResult = new CommandResult("feedback", true, true, false, true);
        assertFalse(falseCommandResult.isShowCustomerSelection());

        falseCommandResult = new CommandResult("feedback", true, true, null, null);
        assertFalse(falseCommandResult.isShowCustomerSelection());

        CommandResult trueCommandResult = new CommandResult("feedback", true, true, true, false);
        assertTrue(trueCommandResult.isShowCustomerSelection());

        trueCommandResult = new CommandResult("feedback", true, true, 0, null);
        assertTrue(trueCommandResult.isShowCustomerSelection());
    }

    @Test
    public void isShowOrderSelection() {
        CommandResult falseCommandResult = new CommandResult("feedback");
        assertFalse(falseCommandResult.isShowOrderSelection());

        falseCommandResult = new CommandResult("feedback", true, true, true, false);
        assertFalse(falseCommandResult.isShowOrderSelection());

        falseCommandResult = new CommandResult("feedback", true, true, 0, null);
        assertFalse(falseCommandResult.isShowOrderSelection());

        CommandResult trueCommandResult = new CommandResult("feedback", true, true, null, 0);
        assertTrue(trueCommandResult.isShowOrderSelection());

        trueCommandResult = new CommandResult("feedback", true, true, false, true);
        assertTrue(trueCommandResult.isShowOrderSelection());
    }

    @Test
    public void showHelp() {
        CommandResult trueCommandResult = new CommandResult("feedback", true, false, null, null);
        assertTrue(trueCommandResult.isShowHelp());

        CommandResult falseCommandResult = new CommandResult("feedback", false, false, null, null);
        assertFalse(falseCommandResult.isShowHelp());
    }

    @Test
    public void exit() {
        CommandResult trueCommandResult = new CommandResult("feedback", false, true);
        assertTrue(trueCommandResult.isExit());

        CommandResult falseCommandResult = new CommandResult("feedback", false, false);
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
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());

        // different showCustomerSelection value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, true, false).hashCode());

        // different showOrderSelection value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, false, true).hashCode());

        // different customer index -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, 0, null).hashCode());

        // different order index -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, null, 0).hashCode());
    }
}
