package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.LoadCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.results.LoadCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class LoadCommandTest {

    private static final Path PATH = Paths.get("src", "test", "data", "SampleData", "fullSampleData.json");
    private static final Path DIFF_PATH = Paths.get("src", "test", "data", "different.json");
    private static final LoadCommand LOAD_COMMAND = new LoadCommand(PATH);
    private static final LoadCommand DIFF_COMMAND = new LoadCommand(DIFF_PATH);

    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalEduMate(), new UserPrefs());

    @Test
    public void constructor_validArgs_success() {
        assertCommandSuccess(LOAD_COMMAND, model,
                new LoadCommandResult(String.format(MESSAGE_SUCCESS, PATH), PATH), expectedModel);
    }

    @Test
    public void equals() {
        // same object
        assertEquals(LOAD_COMMAND, LOAD_COMMAND);
        assertEquals(DIFF_COMMAND, DIFF_COMMAND);

        // same filePath
        assertEquals(LOAD_COMMAND, new LoadCommand(PATH));
        assertEquals(DIFF_COMMAND, new LoadCommand(DIFF_PATH));

        // null
        assertNotEquals(LOAD_COMMAND, null);

        // different type
        assertNotEquals(LOAD_COMMAND, new SaveCommand(PATH));

        // different filePath
        assertNotEquals(LOAD_COMMAND, DIFF_COMMAND);
    }
}
