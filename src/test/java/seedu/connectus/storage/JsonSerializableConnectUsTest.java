package seedu.connectus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.connectus.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.connectus.commons.exceptions.IllegalValueException;
import seedu.connectus.commons.util.JsonUtil;
import seedu.connectus.model.ConnectUs;
import seedu.connectus.testutil.TypicalPersons;

public class JsonSerializableConnectUsTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableConnectUsTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsConnectUs.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonConnectUs.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonConnectUs.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableConnectUs dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableConnectUs.class).get();
        ConnectUs connectUsFromFile = dataFromFile.toModelType();
        ConnectUs typicalPersonsConnectUS = TypicalPersons.getTypicalConnectUs();
        assertEquals(connectUsFromFile, typicalPersonsConnectUS);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableConnectUs dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableConnectUs.class).get();
        assertThrows(IllegalArgumentException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableConnectUs dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableConnectUs.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableConnectUs.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
