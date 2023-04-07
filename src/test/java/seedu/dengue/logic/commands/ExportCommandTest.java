package seedu.dengue.logic.commands;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.dengue.commons.exceptions.DataConversionException;
import seedu.dengue.commons.util.FileUtil;
import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.Model;
import seedu.dengue.model.ModelManager;
import seedu.dengue.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code ExportCommand}.
 */
public class ExportCommandTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "ImportExportTest");

    private static final String TYPICAL_PERSONS_FILE = "typicalPersonsDengueHotspotTracker.csv";

    private static final String EMPTY_PERSONS_FILE = "emptyDengueHotspotTracker.csv";

    @TempDir
    public Path testFolder;


    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void execute_exportTypicalPersonsListToCsv_success() throws IOException, DataConversionException {
        Model model = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        Path filePath = testFolder.resolve("TempDengueHotspotTracker.csv");
        ExportCommand exportCommand = new ExportCommand(filePath);
        String expectedMessage = String.format(ExportCommand.MESSAGE_SUCCESS,
                filePath);

        expectedModel.exportCsv(filePath);
        assertCommandSuccess(exportCommand, model, expectedMessage, expectedModel);
        model = new ModelManager(new DengueHotspotTracker(), new UserPrefs());
        model.importCsv(filePath);
        assertArrayEquals(model.getFilteredPersonList().toArray(), expectedModel.getFilteredPersonList().toArray());
    }

    @Test
    public void execute_exportEmptyCsv_success() throws CommandException, IOException {
        Path filePath = testFolder.resolve("TempEmptyDengueHotspotTracker.csv");
        Model model = new ModelManager(new DengueHotspotTracker(), new UserPrefs());
        Model expectedModel = new ModelManager(new DengueHotspotTracker(), new UserPrefs());

        ExportCommand exportCommand = new ExportCommand(filePath);
        String expectedMessage = String.format(ExportCommand.MESSAGE_SUCCESS,
                filePath);
        assertCommandSuccess(exportCommand, model, expectedMessage, expectedModel);

        assertEquals(expectedMessage, exportCommand.execute(model).toString());
        assertEquals(FileUtil.readFromFile(addToTestDataPathIfNotNull(EMPTY_PERSONS_FILE)),
                FileUtil.readFromFile(filePath));
    }
}
