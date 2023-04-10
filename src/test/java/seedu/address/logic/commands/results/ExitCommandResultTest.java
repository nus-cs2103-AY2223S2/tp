package seedu.address.logic.commands.results;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ExitCommandResultTest {
    private static final String FEEDBACK = "feedback";
    private static final String DIFFERENT = "different";
    private static final ExitCommandResult EXIT_COMMAND_RESULT = new ExitCommandResult(FEEDBACK);

    @Test
    public void equals() {
        // same values -> returns true
        assertEquals(EXIT_COMMAND_RESULT, new ExitCommandResult(FEEDBACK));

        // same object -> returns true
        assertEquals(EXIT_COMMAND_RESULT, EXIT_COMMAND_RESULT);

        // null -> returns false
        assertNotEquals(null, EXIT_COMMAND_RESULT);

        // different types -> returns false
        assertNotEquals(0.5f, EXIT_COMMAND_RESULT);

        // different feedbackToUser value -> returns false
        assertNotEquals(EXIT_COMMAND_RESULT, new ExitCommandResult(DIFFERENT));
    }

    @Test
    public void hashcode() {
        // same values -> returns same hashcode
        assertEquals(EXIT_COMMAND_RESULT.hashCode(), new ExitCommandResult(FEEDBACK).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(EXIT_COMMAND_RESULT.hashCode(), new ExitCommandResult(DIFFERENT).hashCode());
    }
}
