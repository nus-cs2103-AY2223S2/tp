package seedu.address.logic.commands.results;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class LoadCommandResultTest {
    private static final String FEEDBACK = "feedback";
    private static final String DIFFERENT = "different";
    private static final Path FILE_PATH = Paths.get("edumate.json");
    private static final Path DIFF_FILE_PATH = Paths.get("different.json");
    private static final LoadCommandResult LOAD_COMMAND_RESULT = new LoadCommandResult(FEEDBACK, FILE_PATH);

    @Test
    public void equals() {
        // same values -> returns true
        assertEquals(LOAD_COMMAND_RESULT, new LoadCommandResult(FEEDBACK, FILE_PATH));

        // same object -> returns true
        assertEquals(LOAD_COMMAND_RESULT, LOAD_COMMAND_RESULT);

        // null -> returns false
        assertNotEquals(null, LOAD_COMMAND_RESULT);

        // different types -> returns false
        assertNotEquals(0.5f, LOAD_COMMAND_RESULT);

        // different feedbackToUser value -> returns false
        assertNotEquals(LOAD_COMMAND_RESULT, new LoadCommandResult(DIFFERENT, FILE_PATH));

        // different filePath value -> returns false
        assertNotEquals(LOAD_COMMAND_RESULT, new LoadCommandResult(FEEDBACK, DIFF_FILE_PATH));

        // different feedbackToUser and filePath values -> returns false
        assertNotEquals(LOAD_COMMAND_RESULT, new LoadCommandResult(DIFFERENT, DIFF_FILE_PATH));
    }

    @Test
    public void hashcode() {
        // same values -> returns same hashcode
        assertEquals(LOAD_COMMAND_RESULT.hashCode(), new LoadCommandResult(FEEDBACK, FILE_PATH).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(LOAD_COMMAND_RESULT.hashCode(), new LoadCommandResult(DIFFERENT, FILE_PATH).hashCode());

        // different filePath value -> returns different hashcode
        assertNotEquals(LOAD_COMMAND_RESULT.hashCode(), new LoadCommandResult(FEEDBACK, DIFF_FILE_PATH).hashCode());

        // different feedbackToUser and filePath values -> returns different hashcode
        assertNotEquals(LOAD_COMMAND_RESULT.hashCode(), new LoadCommandResult(DIFFERENT, DIFF_FILE_PATH).hashCode());
    }
}
