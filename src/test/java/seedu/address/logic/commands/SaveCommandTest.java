package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SaveCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.results.SaveCommandResult;
import seedu.address.model.EduMateHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SaveCommandTest {

    private static final Path PATH = Paths.get("src", "test", "data", "edumate.json");
    private static final Path DIFF_PATH = Paths.get("src", "test", "data", "different.json");
    private static final SaveCommand SAVE_COMMAND = new SaveCommand(PATH);
    private static final SaveCommand DIFF_COMMAND = new SaveCommand(DIFF_PATH);

    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs(), new EduMateHistory());
    private final Model expectedModel = new ModelManager(getTypicalEduMate(), new UserPrefs(), new EduMateHistory());

    @Test
    public void constructor_validArgs_success() {
        assertCommandSuccess(SAVE_COMMAND, model,
                new SaveCommandResult(String.format(MESSAGE_SUCCESS, PATH), PATH), expectedModel);
    }

    @Test
    public void equals() {
        // same object
        assertEquals(SAVE_COMMAND, SAVE_COMMAND);
        assertEquals(DIFF_COMMAND, DIFF_COMMAND);

        // same filePath
        assertEquals(SAVE_COMMAND, new SaveCommand(PATH));
        assertEquals(DIFF_COMMAND, new SaveCommand(DIFF_PATH));

        // null
        assertNotEquals(SAVE_COMMAND, null);

        // different type
        assertNotEquals(SAVE_COMMAND, new LoadCommand(PATH));

        // different filePath
        assertNotEquals(SAVE_COMMAND, DIFF_COMMAND);
    }
}
