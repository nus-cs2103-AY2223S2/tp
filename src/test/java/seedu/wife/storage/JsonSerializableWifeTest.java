package seedu.wife.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.wife.commons.exceptions.IllegalValueException;
import seedu.wife.commons.util.JsonUtil;
import seedu.wife.model.Wife;
import seedu.wife.testutil.TypicalWife;

public class JsonSerializableWifeTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableWifeTest");
    private static final Path TYPICAL_FOODS_FILE = TEST_DATA_FOLDER.resolve("typicalFoodWife.json");
    private static final Path INVALID_FOOD_FILE = TEST_DATA_FOLDER.resolve("invalidFoodWife.json");
    private static final Path DUPLICATE_FOOD_FILE = TEST_DATA_FOLDER.resolve("duplicateFoodWife.json");

    @Test
    public void toModelType_typicalFoodFile_success() throws Exception {
        JsonSerializableWife dataFromFile = JsonUtil
            .readJsonFile(
                TYPICAL_FOODS_FILE,
                JsonSerializableWife.class
            ).get();

        Wife wifeFromFile = dataFromFile.toModelType();
        Wife typicalWife = TypicalWife.getTypicalWife();

        assertEquals(wifeFromFile, typicalWife);
    }

    @Test
    public void toModelType_invalidFoodFile_throwsIllegalValueException() throws Exception {
        JsonSerializableWife dataFromFile = JsonUtil.readJsonFile(INVALID_FOOD_FILE,
                JsonSerializableWife.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFoods_throwsIllegalValueException() throws Exception {
        JsonSerializableWife dataFromFile = JsonUtil
            .readJsonFile(DUPLICATE_FOOD_FILE, JsonSerializableWife.class).get();
        assertThrows(
            IllegalValueException.class,
            dataFromFile::toModelType,
            JsonSerializableWife.MESSAGE_DUPLICATE_FOODS
        );
    }
}
