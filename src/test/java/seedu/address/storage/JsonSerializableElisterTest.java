package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Elister;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableElisterTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableElisterTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsElister.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonElister.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonElister.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableElister dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableElister.class).get();
        Elister elisterFromFile = dataFromFile.toModelType();
        Elister typicalPersonsElister = TypicalPersons.getTypicalElister();

        assertEquals(elisterFromFile, typicalPersonsElister);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableElister dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableElister.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableElister dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableElister.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableElister.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
