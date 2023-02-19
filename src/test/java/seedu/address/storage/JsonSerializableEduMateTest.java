package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.EduMate;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableEduMateTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableEduMateTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsEduMate.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonEduMate.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonEduMate.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableEduMate dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableEduMate.class).get();
        EduMate eduMateFromFile = dataFromFile.toModelType();
        EduMate typicalPersonsEduMate = TypicalPersons.getTypicalEduMate();
        assertEquals(eduMateFromFile, typicalPersonsEduMate);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableEduMate dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableEduMate.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableEduMate dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableEduMate.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableEduMate.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
