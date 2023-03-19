package ezschedule.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ezschedule.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import ezschedule.commons.exceptions.IllegalValueException;
import ezschedule.commons.util.JsonUtil;
import ezschedule.model.Scheduler;
import ezschedule.testutil.Assert;
import ezschedule.testutil.TypicalPersons;


public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableScheduler dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableScheduler.class).get();
        Scheduler addressBookFromFile = dataFromFile.toModelType();
        Scheduler typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableScheduler dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableScheduler.class).get();
        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableScheduler dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableScheduler.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableScheduler.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
