package teambuilder.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static teambuilder.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import teambuilder.commons.exceptions.IllegalValueException;
import teambuilder.commons.util.JsonUtil;
import teambuilder.model.TeamBuilder;
import teambuilder.testutil.TypicalPersons;

public class JsonSerializableTeamBuilderTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableTeamBuilder dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableTeamBuilder.class).get();
        TeamBuilder addressBookFromFile = dataFromFile.toModelType();
        TeamBuilder typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTeamBuilder dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableTeamBuilder.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableTeamBuilder dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableTeamBuilder.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTeamBuilder.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
