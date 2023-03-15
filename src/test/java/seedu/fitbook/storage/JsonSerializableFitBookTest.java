package seedu.fitbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fitbook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.fitbook.commons.exceptions.IllegalValueException;
import seedu.fitbook.commons.util.JsonUtil;
import seedu.fitbook.model.FitBook;
import seedu.fitbook.testutil.client.TypicalClients;

public class JsonSerializableFitBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFitBookTest");
    private static final Path TYPICAL_CLIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalClientsFitBook.json");
    private static final Path INVALID_CLIENT_FILE = TEST_DATA_FOLDER.resolve("invalidClientFitBook.json");
    private static final Path DUPLICATE_CLIENT_FILE = TEST_DATA_FOLDER.resolve("duplicateClientFitBook.json");

    @Test
    public void toFitBookModelType_typicalClientsFile_success() throws Exception {
        JsonSerializableFitBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_CLIENTS_FILE,
                JsonSerializableFitBook.class).get();
        FitBook fitBookFromFile = dataFromFile.toFitBookModelType();
        FitBook typicalClientsFitBook = TypicalClients.getTypicalFitBook();
        assertEquals(fitBookFromFile, typicalClientsFitBook);
    }

    @Test
    public void toFitBookModelType_invalidClientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFitBook dataFromFile = JsonUtil.readJsonFile(INVALID_CLIENT_FILE,
                JsonSerializableFitBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toFitBookModelType);
    }

    @Test
    public void toFitBookModelType_duplicateClients_throwsIllegalValueException() throws Exception {
        JsonSerializableFitBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CLIENT_FILE,
                JsonSerializableFitBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFitBook.MESSAGE_DUPLICATE_CLIENT,
                dataFromFile::toFitBookModelType);
    }

}
