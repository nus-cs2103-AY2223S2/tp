package seedu.dengue.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.dengue.commons.exceptions.DataConversionException;
import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;
import seedu.dengue.model.ModelManager;
import seedu.dengue.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code ImportCommand}.
 */
public class ImportCommandTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "ImportExportTest");

    private static final String TYPICAL_PERSONS_FILE = "typicalPersonsDengueHotspotTracker.csv";

    private static final String EMPTY_PERSONS_FILE = "emptyDengueHotspotTrackerOne.csv";

    private Model model = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());

    private Model expectedModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void execute_importCsv_success() {
        Model model = new ModelManager();
        Path filePath = addToTestDataPathIfNotNull(TYPICAL_PERSONS_FILE);
        ImportCommand command = new ImportCommand(filePath);
        String expectedMessage = String.format(ImportCommand.MESSAGE_SUCCESS,
                filePath);
        try {
            expectedModel.importCsv(filePath);
        } catch (DataConversionException | IOException e) {
            assert false;
            return;
        }
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_importEmptyCsv_failure() throws CommandException {
        Path filePath = addToTestDataPathIfNotNull(EMPTY_PERSONS_FILE);
        ImportCommand importCommand = new ImportCommand(filePath);
        String expectedMessage = String.format(ImportCommand.MESSAGE_FAILURE_IMPORT,
                filePath);
        assertEquals(expectedMessage, importCommand.execute(model).toString());
    }
}
