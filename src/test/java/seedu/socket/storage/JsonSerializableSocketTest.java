package seedu.socket.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.socket.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.socket.commons.exceptions.IllegalValueException;
import seedu.socket.commons.util.JsonUtil;
import seedu.socket.model.Socket;
import seedu.socket.testutil.TypicalPersons;

public class JsonSerializableSocketTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableSocketTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsSocket.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonSocket.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonSocket.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableSocket dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableSocket.class).get();
        Socket socketFromFile = dataFromFile.toModelType();
        Socket typicalPersonsSocket = TypicalPersons.getTypicalSocket();
        assertEquals(socketFromFile, typicalPersonsSocket);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSocket dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableSocket.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableSocket dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableSocket.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSocket.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
