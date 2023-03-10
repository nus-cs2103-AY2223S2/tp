package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    private final CommandResult commandResult = new CommandResult(AddCommand.MESSAGE_SUCCESS);

    @Test
    void getFeedbackToUser() {
        assertEquals(commandResult.getFeedbackToUser(), AddCommand.MESSAGE_SUCCESS);
    }

    @Test
    void getTargetClient() {
        assertNull(commandResult.getTargetClient());
    }

    @Test
    void isSelect() {
        assertFalse(commandResult.isSelect());
    }

    @Test
    void isShowHelp() {
        assertFalse(commandResult.isShowHelp());
    }

    @Test
    void isExit() {
        assertFalse(commandResult.isExit());
    }

    @Test
    void testEquals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertEquals(commandResult, new CommandResult("feedback"));
        assertEquals(commandResult, new CommandResult("feedback", false, false, false));

        // same object -> returns true
        assertEquals(commandResult, commandResult);

        // null -> returns false
        assertNotEquals(null, commandResult);

        // different types -> returns false
        assertNotEquals(commandResult, 0.0);

        // different feedbackToUser value -> returns false
        assertNotEquals(commandResult, new CommandResult("different"));

        // different select value ->  returns false
        assertNotEquals(commandResult, new CommandResult("feedback", true, false, false));

        // different showHelp value -> returns false
        assertNotEquals(commandResult, new CommandResult("feedback", false, true, false));

        // different exit value -> returns false
        assertNotEquals(commandResult, new CommandResult("feedback", false, false, true));
    }

    @Test
    void testHashCode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different select value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, false).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, true).hashCode());
    }
}
