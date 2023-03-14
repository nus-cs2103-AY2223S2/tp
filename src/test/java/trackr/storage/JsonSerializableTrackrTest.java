package trackr.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackr.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import trackr.commons.exceptions.IllegalValueException;
import trackr.commons.util.JsonUtil;
import trackr.model.AddressBook;
import trackr.model.OrderList;
import trackr.model.TaskList;
import trackr.testutil.TypicalPersons;
import trackr.testutil.TypicalTasks;

public class JsonSerializableTrackrTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTrackrTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsTrackr.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonTrackr.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonTrackr.json");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTasksTrackr.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskTrackr.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskTrackr.json");
    private static final Path TYPICAL_ORDERS_FILE = TEST_DATA_FOLDER.resolve("typicalOrdersTrackr.json");
    private static final Path INVALID_ORDER_FILE = TEST_DATA_FOLDER.resolve("invalidOrderTrackr.json");
    private static final Path DUPLICATE_ORDER_FILE = TEST_DATA_FOLDER.resolve("duplicateOrderTrackr.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableTrackr dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableTrackr.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackr dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableTrackr.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackr dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableTrackr.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTrackr.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toTaskModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableTrackr dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableTrackr.class).get();
        TaskList taskListFromFile = dataFromFile.toTaskModelType();
        TaskList typicalTaskList = TypicalTasks.getTypicalTaskList();
        assertEquals(taskListFromFile, typicalTaskList);
    }

    @Test
    public void toTaskModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackr dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableTrackr.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toTaskModelType);
    }

    @Test
    public void toTaskModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackr dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializableTrackr.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTrackr.MESSAGE_DUPLICATE_TASK,
                dataFromFile::toTaskModelType);
    }

    @Test
    public void toOrderModelType_typicalOrderFile_sucess() throws Exception {
        JsonSerializableTrackr dataFromFile = JsonUtil.readJsonFile(TYPICAL_ORDERS_FILE,
                JsonSerializableTrackr.class).get();
        OrderList orderListFromFile = dataFromFile.toOrderModelType();
        assertEquals(orderListFromFile, orderListFromFile);
    }

    @Test
    public void toOrderModelType_invalidOrderFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackr dataFromFile = JsonUtil.readJsonFile(INVALID_ORDER_FILE,
                JsonSerializableTrackr.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toOrderModelType);
    }

    @Test
    public void toOrderModelType_duplicateOrders_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackr dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ORDER_FILE,
                JsonSerializableTrackr.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTrackr.MESSAGE_DUPLICATE_ORDER,
                dataFromFile::toOrderModelType);
    }

}
