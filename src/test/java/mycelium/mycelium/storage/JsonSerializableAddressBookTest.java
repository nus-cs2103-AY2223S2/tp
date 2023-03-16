package mycelium.mycelium.storage;

import static mycelium.mycelium.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.exceptions.IllegalValueException;
import mycelium.mycelium.commons.util.JsonUtil;
import mycelium.mycelium.model.AddressBook;
import mycelium.mycelium.testutil.TypicalPersons;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");
    private static final Path DUPLICATE_CLIENT_FILE = TEST_DATA_FOLDER.resolve("duplicateClientAddressBook.json");
    private static final Path DUPLICATE_PROJECT_FILE = TEST_DATA_FOLDER.resolve("duplicateProjectAddressBook.json");

    @Test
    public void toModelType_typicalAddressBookFile_success() throws Exception {
        // TODO (jy): update the name of this test
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
            JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(typicalAddressBook, addressBookFromFile);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        // TODO (jy): update this test to test for invalid clients and/or projects
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
            JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
            JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_PERSON,
            dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateClients_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CLIENT_FILE,
            JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_CLIENT,
            dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProjects_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PROJECT_FILE,
            JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_PROJECT,
            dataFromFile::toModelType);
    }
}
