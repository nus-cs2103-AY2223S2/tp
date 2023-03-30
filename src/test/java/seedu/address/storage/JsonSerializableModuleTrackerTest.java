package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ModuleTracker;
import seedu.address.testutil.TypicalModules;

public class JsonSerializableModuleTrackerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableModuleTrackerTest");
    private static final Path TYPICAL_MODULES_FILE = TEST_DATA_FOLDER.resolve("typicalModulesModuleTracker.json");
    private static final Path INVALID_MODULE_FILE = TEST_DATA_FOLDER.resolve("invalidModuleModuleTracker.json");
    private static final Path DUPLICATE_MODULE_FILE = TEST_DATA_FOLDER.resolve("duplicateModuleModuleTracker.json");

    @Test
    public void toModelType_typicalModulesFile_success() throws Exception {
        JsonSerializableModuleTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_MODULES_FILE,
                JsonSerializableModuleTracker.class).get();
        ModuleTracker moduleTrackerFromFile = dataFromFile.toModelType();
        ModuleTracker typicalModulesModuleTracker = TypicalModules.getTypicalModuleTracker();
        assertEquals(moduleTrackerFromFile, typicalModulesModuleTracker);
    }

    @Test
    public void toModelType_invalidModuleFile_throwsIllegalValueException() throws Exception {
        JsonSerializableModuleTracker dataFromFile = JsonUtil.readJsonFile(INVALID_MODULE_FILE,
                JsonSerializableModuleTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateModules_throwsIllegalValueException() throws Exception {
        JsonSerializableModuleTracker dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULE_FILE,
                JsonSerializableModuleTracker.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableModuleTracker.MESSAGE_DUPLICATE_MODULE,
                dataFromFile::toModelType);
    }

}
