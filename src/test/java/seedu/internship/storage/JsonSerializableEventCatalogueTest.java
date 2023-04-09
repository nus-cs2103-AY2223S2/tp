package seedu.internship.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internship.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.internship.commons.exceptions.IllegalValueException;
import seedu.internship.commons.util.JsonUtil;
import seedu.internship.model.EventCatalogue;
import seedu.internship.testutil.TypicalEvents;

public class JsonSerializableEventCatalogueTest {

    private static final Path TEST_DATA_FOLDER = Paths.get(
            "src", "test", "data", "JsonSerializableEventCatalogueTest");
    private static final Path TYPICAL_EVENT_FILE = TEST_DATA_FOLDER.resolve(
            "typicalEventEventCatalogue.json");
    private static final Path INVALID_EVENT_FILE = TEST_DATA_FOLDER.resolve(
            "invalidEventEventCatalogue.json");
    private static final Path DUPLICATE_EVENT_FILE = TEST_DATA_FOLDER.resolve(
            "duplicateEventEventCatalogue.json");

    @Test
    public void toModelType_typicalEventsFile_success() throws Exception {
        JsonSerializableEventCatalogue dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENT_FILE,
                JsonSerializableEventCatalogue.class).get();
        EventCatalogue eventCatalogueFromFile = dataFromFile.toModelType();
        EventCatalogue typicalEventsEventCatalogue = TypicalEvents.getTypicalEventCatalogue();
        assertEquals(eventCatalogueFromFile, typicalEventsEventCatalogue);
    }

    @Test
    public void toModelType_invalidEventsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableEventCatalogue dataFromFile = JsonUtil.readJsonFile(INVALID_EVENT_FILE,
                JsonSerializableEventCatalogue.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEvents_throwsIllegalValueException() throws Exception {
        JsonSerializableEventCatalogue dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EVENT_FILE,
                JsonSerializableEventCatalogue.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableEventCatalogue.MESSAGE_DUPLICATE_EVENT,
                dataFromFile::toModelType);
    }
}
