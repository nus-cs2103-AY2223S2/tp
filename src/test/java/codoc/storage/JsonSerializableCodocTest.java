package codoc.storage;

import static codoc.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import codoc.commons.exceptions.IllegalValueException;
import codoc.commons.util.JsonUtil;
import codoc.model.Codoc;
import codoc.testutil.TypicalPersons;

public class JsonSerializableCodocTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableCodocTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsCodoc.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonCodoc.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonCodoc.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableCodoc dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableCodoc.class).get();
        Codoc codocFromFile = dataFromFile.toModelType();
        Codoc typicalPersonsCodoc = TypicalPersons.getTypicalCodoc();
        assertEquals(codocFromFile, typicalPersonsCodoc);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCodoc dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableCodoc.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableCodoc dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableCodoc.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableCodoc.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
