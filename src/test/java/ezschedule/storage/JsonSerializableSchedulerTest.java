package ezschedule.storage;

import static ezschedule.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import ezschedule.commons.exceptions.IllegalValueException;
import ezschedule.commons.util.JsonUtil;
import ezschedule.model.Scheduler;
import ezschedule.testutil.TypicalEvents;

public class JsonSerializableSchedulerTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableSchedulerTest");
    private static final Path TYPICAL_EVENTS_FILE = TEST_DATA_FOLDER.resolve("typicalEventsScheduler.json");
    private static final Path INVALID_EVENT_FILE = TEST_DATA_FOLDER.resolve("invalidEventScheduler.json");
    private static final Path DUPLICATE_EVENT_FILE = TEST_DATA_FOLDER.resolve("duplicateEventScheduler.json");

    @Test
    public void toModelType_typicalEventsFile_success() throws Exception {
        JsonSerializableScheduler dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENTS_FILE,
                JsonSerializableScheduler.class).get();
        Scheduler schedulerFromFile = dataFromFile.toModelType();
        Scheduler typicalEventsScheduler = TypicalEvents.getTypicalScheduler();
        assertEquals(schedulerFromFile, typicalEventsScheduler);
    }

    @Test
    public void toModelType_invalidEventFile_throwsIllegalValueException() throws Exception {
        JsonSerializableScheduler dataFromFile = JsonUtil.readJsonFile(INVALID_EVENT_FILE,
                JsonSerializableScheduler.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEvents_throwsIllegalValueException() throws Exception {
        JsonSerializableScheduler dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EVENT_FILE,
                JsonSerializableScheduler.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableScheduler.MESSAGE_DUPLICATE_EVENT,
                dataFromFile::toModelType);
    }
}
