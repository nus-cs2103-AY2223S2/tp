package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {

    private static final String FEEDBACK = "Feedback message";
    private static final boolean SHOW_HELP = true;
    private static final boolean EXIT = true;

    @Test
    public void constructor_feedbackOnly() {
        CommandResult commandResult = new CommandResult(FEEDBACK);
        assertEquals(FEEDBACK, commandResult.getFeedbackToUser());
        assertFalse(commandResult.isShowHelp());
        assertFalse(commandResult.isExit());
    }

    @Test
    public void constructor_allFields() {
        CommandResult commandResult = new CommandResult(FEEDBACK, SHOW_HELP, EXIT);
        assertEquals(FEEDBACK, commandResult.getFeedbackToUser());
        assertTrue(commandResult.isShowHelp());
        assertTrue(commandResult.isExit());
    }

    @Test
    public void equals_differentTypes() {
        CommandResult commandResult = new CommandResult(FEEDBACK);
        assertFalse(commandResult.equals(null));
        assertFalse(commandResult.equals(new Object()));
    }

    @Test
    public void equals_sameInstance() {
        CommandResult commandResult = new CommandResult(FEEDBACK);
        assertTrue(commandResult.equals(commandResult));
    }

    @Test
    public void equals_differentValues() {
        CommandResult commandResult = new CommandResult(FEEDBACK, SHOW_HELP, EXIT);

        // Different feedbackToUser value
        assertFalse(commandResult.equals(new CommandResult("Different feedback", SHOW_HELP, EXIT)));

        // Different showHelp value
        assertFalse(commandResult.equals(new CommandResult(FEEDBACK, false, EXIT)));

        // Different exit value
        assertFalse(commandResult.equals(new CommandResult(FEEDBACK, SHOW_HELP, false)));
    }

    @Test
    public void hashCode_equalObjects() {
        CommandResult commandResult1 = new CommandResult(FEEDBACK, SHOW_HELP, EXIT);
        CommandResult commandResult2 = new CommandResult(FEEDBACK, SHOW_HELP, EXIT);
        assertEquals(commandResult1.hashCode(), commandResult2.hashCode());
    }

    @Test
    public void hashCode_differentObjects() {
        CommandResult commandResult1 = new CommandResult(FEEDBACK, SHOW_HELP, EXIT);

        // Different feedbackToUser value
        CommandResult commandResult2 = new CommandResult("Different feedback", SHOW_HELP, EXIT);
        assertNotEquals(commandResult1.hashCode(), commandResult2.hashCode());

        // Different showHelp value
        CommandResult commandResult3 = new CommandResult(FEEDBACK, false, EXIT);
        assertNotEquals(commandResult1.hashCode(), commandResult3.hashCode());

        // Different exit value
        CommandResult commandResult4 = new CommandResult(FEEDBACK, SHOW_HELP, false);
        assertNotEquals(commandResult1.hashCode(), commandResult4.hashCode());
    }

}
