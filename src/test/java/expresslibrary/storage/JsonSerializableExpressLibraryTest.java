package expresslibrary.storage;

import static expresslibrary.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import expresslibrary.commons.exceptions.IllegalValueException;
import expresslibrary.commons.util.JsonUtil;
import expresslibrary.model.ExpressLibrary;
import expresslibrary.testutil.TypicalExpressLibrary;

public class JsonSerializableExpressLibraryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableExpressLibraryTest");
    private static final Path TYPICAL_EXPRESSLIBRARY_FILE = TEST_DATA_FOLDER.resolve("typicalExpressLibrary.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonExpressLibrary.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonExpressLibrary.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableExpressLibrary dataFromFile = JsonUtil.readJsonFile(TYPICAL_EXPRESSLIBRARY_FILE,
                JsonSerializableExpressLibrary.class).get();
        ExpressLibrary expressLibraryFromFile = dataFromFile.toModelType();
        ExpressLibrary typicalExpressLibrary = TypicalExpressLibrary.getTypicalExpressLibrary();
        assertEquals(expressLibraryFromFile, typicalExpressLibrary);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableExpressLibrary dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableExpressLibrary.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableExpressLibrary dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableExpressLibrary.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableExpressLibrary.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
