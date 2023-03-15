package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Tracker;
import seedu.address.testutil.TypicalModules;

public class JsonSerializableTrackerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTrackerTest");
    private static final Path TYPICAL_TRACKER_FILE = TEST_DATA_FOLDER.resolve("typicalTracker.json");
    private static final Path INVALID_MODULE_FILE = TEST_DATA_FOLDER.resolve("invalidModuleTracker.json");
    private static final Path DUPLICATE_MODULE_FILE = TEST_DATA_FOLDER.resolve("duplicateModuleTracker.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_TRACKER_FILE,
                JsonSerializableTracker.class).get();
        Tracker trackerFromFile = dataFromFile.toModelType();
        Tracker typicalTracker = TypicalModules.getTypicalTracker();
        assertEquals(trackerFromFile, typicalTracker);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTracker dataFromFile = JsonUtil.readJsonFile(INVALID_MODULE_FILE,
                JsonSerializableTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableTracker dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULE_FILE,
                JsonSerializableTracker.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTracker.MESSAGE_DUPLICATE_MODULE,
                dataFromFile::toModelType);
    }

}
