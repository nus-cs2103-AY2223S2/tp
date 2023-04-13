package mycelium.mycelium.logic.commands.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.logic.uiaction.ExitAction;
import mycelium.mycelium.logic.uiaction.ShowHelpAction;
import mycelium.mycelium.logic.uiaction.UiAction;

public class CommandExceptionTest {
    @Test
    public void constructor_nullAction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CommandException("message", (UiAction) null));
    }

    @Test
    public void constructor_validMessage_success() {
        CommandException commandException = new CommandException("message", new ExitAction());
        assertEquals("message", commandException.getMessage());
    }

    @Test
    public void equals() {
        CommandException commandException =
            new CommandException("message", new ExitAction());
        CommandException commandExceptionCopy =
            new CommandException("message", new ExitAction());
        CommandException commandExceptionWithDifferentMessage =
            new CommandException("different message", new ExitAction());
        CommandException commandExceptionWithDifferentAction =
            new CommandException("message", new ShowHelpAction());
        CommandException commandExceptionWithDifferentMessageAndAction =
            new CommandException("different message", new ShowHelpAction());

        // same object -> returns true
        assertTrue(commandException.equals(commandException));

        // same values -> returns true
        assertTrue(commandException.equals(commandExceptionCopy));

        // different types -> returns false
        assertFalse(commandException.equals(1));

        // null -> returns false
        assertFalse(commandException.equals(null));

        // different message -> returns false
        assertFalse(commandException.equals(commandExceptionWithDifferentMessage));

        // different action -> returns false
        assertFalse(commandException.equals(commandExceptionWithDifferentAction));

        // different message and action -> returns false
        assertFalse(commandException.equals(commandExceptionWithDifferentMessageAndAction));
    }

    @Test
    public void hashCodeTest() {
        CommandException commandException =
            new CommandException("message", new ExitAction());
        CommandException commandExceptionCopy =
            new CommandException("message", new ExitAction());
        CommandException commandExceptionWithDifferentMessage =
            new CommandException("different message", new ExitAction());
        CommandException commandExceptionWithDifferentAction =
            new CommandException("message", new ShowHelpAction());
        CommandException commandExceptionWithDifferentMessageAndAction =
            new CommandException("different message", new ShowHelpAction());

        // same object -> returns same hashcode
        assertEquals(commandException.hashCode(), commandException.hashCode());

        // same values -> returns same hashcode
        assertEquals(commandException.hashCode(), commandExceptionCopy.hashCode());

        // different message -> returns different hashcode
        assertNotEquals(commandException.hashCode(), commandExceptionWithDifferentMessage.hashCode());

        // different action -> returns different hashcode
        assertNotEquals(commandException.hashCode(), commandExceptionWithDifferentAction.hashCode());

        // different message and action -> returns different hashcode
        assertNotEquals(commandException.hashCode(), commandExceptionWithDifferentMessageAndAction.hashCode());
    }

    @Test
    public void getMessage() {
        CommandException commandException = new CommandException("message", new ExitAction());
        assertEquals("message", commandException.getMessage());
    }
}
