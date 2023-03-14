package seedu.dengue.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.dengue.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.dengue.commons.exceptions.IllegalValueException;
import seedu.dengue.commons.util.JsonUtil;
import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.testutil.TypicalPersons;

public class JsonSerializableDengueHotspotTrackerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableDengueHotspotTrackerTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER
            .resolve("typicalPersonsDengueHotspotTracker.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER
            .resolve("invalidPersonDengueHotspotTracker.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER
            .resolve("duplicatePersonDengueHotspotTracker.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableDengueHotspotTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableDengueHotspotTracker.class).get();
        DengueHotspotTracker dengueHotspotTrackerFromFile = dataFromFile.toModelType();
        DengueHotspotTracker typicalPersonsDengueHotspotTracker = TypicalPersons.getTypicalDengueHotspotTracker();
        assertEquals(dengueHotspotTrackerFromFile, typicalPersonsDengueHotspotTracker);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDengueHotspotTracker dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableDengueHotspotTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableDengueHotspotTracker dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableDengueHotspotTracker.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableDengueHotspotTracker.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
