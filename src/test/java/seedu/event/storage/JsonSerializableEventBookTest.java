package seedu.event.storage;

import static seedu.event.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.event.commons.exceptions.IllegalValueException;
import seedu.event.commons.util.JsonUtil;

public class JsonSerializableEventBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableEventBookTest");
    private static final Path TYPICAL_EVENTS_FILE = TEST_DATA_FOLDER.resolve("typicalEventsEventBook.json");
    private static final Path INVALID_EVENT_FILE = TEST_DATA_FOLDER.resolve("invalidEventEventBook.json");
    private static final Path DUPLICATE_EVENT_FILE = TEST_DATA_FOLDER.resolve("duplicateEventEventBook.json");

    //TODO FIX TESTCASE

    //    @Test
    //    public void toModelType_typicalEventsFile_success() throws Exception {
    //        JsonSerializableEventBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENTS_FILE,
    //                JsonSerializableEventBook.class).get();
    //        EventBook addressBookFromFile = dataFromFile.toModelType();
    //        EventBook typicalEventsAddressBook = TypicalEvents.getTypicalAddressBook();
    //        assertEquals(addressBookFromFile, typicalEventsAddressBook);
    //    }

    @Test
    public void toModelType_invalidEventFile_throwsIllegalValueException() throws Exception {
        JsonSerializableEventBook dataFromFile = JsonUtil.readJsonFile(INVALID_EVENT_FILE,
                JsonSerializableEventBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEvents_throwsIllegalValueException() throws Exception {
        JsonSerializableEventBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EVENT_FILE,
                JsonSerializableEventBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableEventBook.MESSAGE_DUPLICATE_EVENT,
                dataFromFile::toModelType);
    }

}
