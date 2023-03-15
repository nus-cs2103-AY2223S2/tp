package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TankList;
import seedu.address.testutil.TypicalTanks;

/**
 * Test class for {@code JsonSerializableTankList}
 */
class JsonSerializableTankListTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTankListTest");
    private static final Path TYPICAL_TANKS_FILE = TEST_DATA_FOLDER.resolve("typicalTanksTankList.json");
    private static final Path INVALID_TANK_FILE = TEST_DATA_FOLDER.resolve("invalidTankTankList.json");
    private static final Path DUPLICATE_TANK_FILE = TEST_DATA_FOLDER.resolve("duplicateTankTankList.json");

    @Test
    public void toModelType_typicalTanksFile_success() throws Exception {
        JsonSerializableTankList dataFromFile = JsonUtil.readJsonFile(TYPICAL_TANKS_FILE,
                JsonSerializableTankList.class).get();
        TankList tankListFromFile = dataFromFile.toModelType();
        TankList typicalTanksTankList = TypicalTanks.getTypicalTankListVersionTwo();
        assertEquals(tankListFromFile, typicalTanksTankList);
    }

    @Test
    public void toModelType_invalidTankFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTankList dataFromFile = JsonUtil.readJsonFile(INVALID_TANK_FILE,
                JsonSerializableTankList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTanks_throwsIllegalValueException() throws Exception {
        JsonSerializableTankList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TANK_FILE,
                JsonSerializableTankList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTankList.MESSAGE_DUPLICATE_TANK,
                dataFromFile::toModelType);
    }

}
