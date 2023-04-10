package seedu.address.logic.commands.results;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class SaveCommandResultTest {
    private static final String FEEDBACK = "feedback";
    private static final String DIFFERENT = "different";
    private static final Path FILE_PATH = Paths.get("edumate.json");
    private static final Path DIFF_FILE_PATH = Paths.get("different.json");
    private static final SaveCommandResult SAVE_COMMAND_RESULT = new SaveCommandResult(FEEDBACK, FILE_PATH);

    @Test
    public void equals() {
        // same values -> returns true
        assertEquals(SAVE_COMMAND_RESULT, new SaveCommandResult(FEEDBACK, FILE_PATH));

        // same object -> returns true
        assertEquals(SAVE_COMMAND_RESULT, SAVE_COMMAND_RESULT);

        // null -> returns false
        assertNotEquals(null, SAVE_COMMAND_RESULT);

        // different types -> returns false
        assertNotEquals(0.5f, SAVE_COMMAND_RESULT);

        // different feedbackToUser value -> returns false
        assertNotEquals(SAVE_COMMAND_RESULT, new SaveCommandResult(DIFFERENT, FILE_PATH));

        // different filePath value -> returns false
        assertNotEquals(SAVE_COMMAND_RESULT, new SaveCommandResult(FEEDBACK, DIFF_FILE_PATH));

        // different feedbackToUser and filePath values -> returns false
        assertNotEquals(SAVE_COMMAND_RESULT, new SaveCommandResult(DIFFERENT, DIFF_FILE_PATH));
    }

    @Test
    public void hashcode() {
        // same values -> returns same hashcode
        assertEquals(SAVE_COMMAND_RESULT.hashCode(), new SaveCommandResult(FEEDBACK, FILE_PATH).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(SAVE_COMMAND_RESULT.hashCode(), new SaveCommandResult(DIFFERENT, FILE_PATH).hashCode());

        // different filePath value -> returns different hashcode
        assertNotEquals(SAVE_COMMAND_RESULT.hashCode(), new SaveCommandResult(FEEDBACK, DIFF_FILE_PATH).hashCode());

        // different feedbackToUser and filePath values -> returns different hashcode
        assertNotEquals(SAVE_COMMAND_RESULT.hashCode(), new SaveCommandResult(DIFFERENT, DIFF_FILE_PATH).hashCode());
    }
}
