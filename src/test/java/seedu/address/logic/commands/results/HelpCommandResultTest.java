package seedu.address.logic.commands.results;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class HelpCommandResultTest {
    private static final String FEEDBACK = "feedback";
    private static final String DIFFERENT = "different";
    private static final HelpCommandResult HELP_COMMAND_RESULT = new HelpCommandResult(FEEDBACK);

    @Test
    public void equals() {
        // same values -> returns true
        assertEquals(HELP_COMMAND_RESULT, new HelpCommandResult(FEEDBACK));

        // same object -> returns true
        assertEquals(HELP_COMMAND_RESULT, HELP_COMMAND_RESULT);

        // null -> returns false
        assertNotEquals(null, HELP_COMMAND_RESULT);

        // different types -> returns false
        assertNotEquals(0.5f, HELP_COMMAND_RESULT);

        // different feedbackToUser value -> returns false
        assertNotEquals(HELP_COMMAND_RESULT, new HelpCommandResult(DIFFERENT));
    }

    @Test
    public void hashcode() {
        // same values -> returns same hashcode
        assertEquals(HELP_COMMAND_RESULT.hashCode(), new HelpCommandResult(FEEDBACK).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(HELP_COMMAND_RESULT.hashCode(), new HelpCommandResult(DIFFERENT).hashCode());
    }
}
