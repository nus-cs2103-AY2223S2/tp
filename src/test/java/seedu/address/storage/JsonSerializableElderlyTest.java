package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalElderly.getTypicalElderly;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.FriendlyLink;
import seedu.address.model.person.Elderly;
import seedu.address.storage.elderly.JsonSerializableElderly;

public class JsonSerializableElderlyTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableElderlyTest");
    private static final Path TYPICAL_ELDERLY_FILE = TEST_DATA_FOLDER.resolve("typicalElderly.json");
    private static final Path INVALID_ELDERLY_FILE = TEST_DATA_FOLDER.resolve("invalidElderly.json");
    private static final Path DUPLICATE_ELDERLY_FILE = TEST_DATA_FOLDER.resolve("duplicateElderly.json");

    @Test
    public void toModelType_typicalElderlyFile_success() throws Exception {
        FriendlyLink appTestCache = new FriendlyLink();
        JsonSerializableElderly dataFromFile = JsonUtil.readJsonFile(TYPICAL_ELDERLY_FILE,
                JsonSerializableElderly.class).get();
        FriendlyLink friendlyLinkFromFile = dataFromFile.toModelType(appTestCache);
        List<Elderly> typicalElderly = getTypicalElderly();
        assertEquals(typicalElderly, friendlyLinkFromFile.getElderlyList());
    }

    @Test
    public void toModelType_invalidElderlyFile_throwsIllegalValueException() throws Exception {
        FriendlyLink appTestCache = new FriendlyLink();
        JsonSerializableElderly dataFromFile = JsonUtil.readJsonFile(INVALID_ELDERLY_FILE,
                JsonSerializableElderly.class).get();
        assertThrows(IllegalValueException.class, () -> dataFromFile.toModelType(appTestCache));
    }

    @Test
    public void toModelType_duplicateElderly_throwsIllegalValueException() throws Exception {
        FriendlyLink appTestCache = new FriendlyLink();
        JsonSerializableElderly dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ELDERLY_FILE,
                JsonSerializableElderly.class).get();
        assertThrows(IllegalValueException.class,
                JsonSerializableElderly.MESSAGE_DUPLICATE_ELDERLY, () -> dataFromFile.toModelType(appTestCache));
    }

}
