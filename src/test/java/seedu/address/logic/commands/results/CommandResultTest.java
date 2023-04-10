package seedu.address.logic.commands.results;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    private static final String FEEDBACK = "feedback";
    private static final String DIFFERENT = "different";
    private static final Path FILE_PATH = Paths.get("edumate.json");
    private static final CommandResult COMMAND_RESULT = new CommandResult(FEEDBACK);
    private static final ExitCommandResult EXIT_COMMAND_RESULT = new ExitCommandResult(FEEDBACK);
    private static final HelpCommandResult HELP_COMMAND_RESULT = new HelpCommandResult(FEEDBACK);
    private static final LoadCommandResult LOAD_COMMAND_RESULT = new LoadCommandResult(FEEDBACK, FILE_PATH);
    private static final SaveCommandResult SAVE_COMMAND_RESULT = new SaveCommandResult(FEEDBACK, FILE_PATH);

    @Test
    public void equals() {
        // same values -> returns true
        assertEquals(COMMAND_RESULT, new CommandResult(FEEDBACK));

        // same object -> returns true
        assertEquals(COMMAND_RESULT, COMMAND_RESULT);

        // null -> returns false
        assertNotEquals(null, COMMAND_RESULT);

        // different types -> returns false
        assertNotEquals(0.5f, COMMAND_RESULT);

        // different feedbackToUser value -> returns false
        assertNotEquals(COMMAND_RESULT, new CommandResult(DIFFERENT));

        // different Command Result types -> returns false
        assertNotEquals(COMMAND_RESULT, EXIT_COMMAND_RESULT);
        assertNotEquals(COMMAND_RESULT, HELP_COMMAND_RESULT);
        assertNotEquals(COMMAND_RESULT, LOAD_COMMAND_RESULT);
        assertNotEquals(COMMAND_RESULT, SAVE_COMMAND_RESULT);
        assertNotEquals(EXIT_COMMAND_RESULT, HELP_COMMAND_RESULT);
        assertNotEquals(EXIT_COMMAND_RESULT, LOAD_COMMAND_RESULT);
        assertNotEquals(EXIT_COMMAND_RESULT, SAVE_COMMAND_RESULT);
        assertNotEquals(HELP_COMMAND_RESULT, LOAD_COMMAND_RESULT);
        assertNotEquals(HELP_COMMAND_RESULT, SAVE_COMMAND_RESULT);
        assertNotEquals(LOAD_COMMAND_RESULT, SAVE_COMMAND_RESULT);
    }

    @Test
    public void hashcode() {
        // same values -> returns same hashcode
        assertEquals(COMMAND_RESULT.hashCode(), new CommandResult(FEEDBACK).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(COMMAND_RESULT.hashCode(), new CommandResult(DIFFERENT).hashCode());

        // different Command Result types -> returns different hashcode
        assertNotEquals(COMMAND_RESULT.hashCode(), EXIT_COMMAND_RESULT.hashCode());
        assertNotEquals(COMMAND_RESULT.hashCode(), HELP_COMMAND_RESULT.hashCode());
        assertNotEquals(COMMAND_RESULT.hashCode(), LOAD_COMMAND_RESULT.hashCode());
        assertNotEquals(COMMAND_RESULT.hashCode(), SAVE_COMMAND_RESULT.hashCode());
        assertNotEquals(EXIT_COMMAND_RESULT.hashCode(), HELP_COMMAND_RESULT.hashCode());
        assertNotEquals(EXIT_COMMAND_RESULT.hashCode(), LOAD_COMMAND_RESULT.hashCode());
        assertNotEquals(EXIT_COMMAND_RESULT.hashCode(), SAVE_COMMAND_RESULT.hashCode());
        assertNotEquals(HELP_COMMAND_RESULT.hashCode(), LOAD_COMMAND_RESULT.hashCode());
        assertNotEquals(HELP_COMMAND_RESULT.hashCode(), SAVE_COMMAND_RESULT.hashCode());
        assertNotEquals(LOAD_COMMAND_RESULT.hashCode(), SAVE_COMMAND_RESULT.hashCode());
    }

    @Test
    public void getFeedbackToUser_validCommandResult_success() {
        CommandResult commandResult = new CommandResult(FEEDBACK);
        assertEquals(FEEDBACK, commandResult.getFeedbackToUser());
    }

    @Test
    public void isShowHelp_false_false() {
        assertFalse(COMMAND_RESULT.isShowHelp());
    }

    @Test
    public void isExit_false_false() {
        assertFalse(COMMAND_RESULT.isExit());
    }

    @Test
    public void isLoad_false_false() {
        assertFalse(COMMAND_RESULT.isLoad());
    }

    @Test
    public void isSave_false_false() {
        assertFalse(COMMAND_RESULT.isSave());
    }
}
