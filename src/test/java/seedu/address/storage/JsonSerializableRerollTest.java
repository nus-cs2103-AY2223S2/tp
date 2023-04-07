package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Reroll;
import seedu.address.testutil.TypicalEntities;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

class JsonSerializableRerollTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableRerollTest");
    private static final Path TYPICAL_ENTITIES_FILE = TEST_DATA_FOLDER.resolve("typicalEntitiesReroll.json");
    private static final Path INVALID_ENTITY_FILE = TEST_DATA_FOLDER.resolve("invalidEntityReroll.json");
    private static final Path DUPLICATE_ENTITY_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    @Test
    public void toModelType_typicalEntitiesFile_success() throws Exception {
        JsonSerializableReroll dataFromFile = JsonUtil.readJsonFile(TYPICAL_ENTITIES_FILE,
                JsonSerializableReroll.class).get();
        Reroll rerollFromFile = dataFromFile.toModelType();
        Reroll typicalEntities = TypicalEntities.getTypicalReroll();
        assertEquals(rerollFromFile, typicalEntities);
    }

    @Test
    public void toModelType_invalidEntityFile_throwsIllegalValueException() throws Exception {
        JsonSerializableReroll dataFromFile = JsonUtil.readJsonFile(INVALID_ENTITY_FILE,
                JsonSerializableReroll.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEntityFile_throwsIllegalValueException() throws Exception {
        JsonSerializableReroll dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ENTITY_FILE,
                JsonSerializableReroll.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
}
