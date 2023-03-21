package seedu.careflow.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.careflow.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.careflow.commons.exceptions.IllegalValueException;
import seedu.careflow.commons.util.JsonUtil;
import seedu.careflow.model.DrugInventory;
import seedu.careflow.testutil.TypicalDrugs;




class JsonSerializableDrugInventoryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableDrugInventoryTest");
    private static final Path TYPICAL_DRUGS_FILE = TEST_DATA_FOLDER.resolve("typicalDrugsDrugInventory.json");
    private static final Path INVALID_DRUG_FILE = TEST_DATA_FOLDER.resolve("invalidDrugDrugInventory.json");
    private static final Path DUPLICATE_DRUG_FILE = TEST_DATA_FOLDER.resolve("duplicateDrugDrugInventory.json");

    @Test
    public void toModelType_typicalDrugsFile_success() throws Exception {
        JsonSerializableDrugInventory dataFromFile = JsonUtil.readJsonFile(TYPICAL_DRUGS_FILE,
                JsonSerializableDrugInventory.class).get();
        DrugInventory drugInventoryFromFile = dataFromFile.toModelType();
        DrugInventory typicalDrugsDrugInventory = TypicalDrugs.getTypicalDrugInventory();
        assertEquals(drugInventoryFromFile, typicalDrugsDrugInventory);
    }

    @Test
    public void toModelType_invalidDrugFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDrugInventory dataFromFile = JsonUtil.readJsonFile(INVALID_DRUG_FILE,
                JsonSerializableDrugInventory.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
    @Test
    public void toModelType_duplicateDrugs_throwsIllegalValueException() throws Exception {
        JsonSerializableDrugInventory dataFromFile = JsonUtil.readJsonFile(DUPLICATE_DRUG_FILE,
                JsonSerializableDrugInventory.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableDrugInventory.MESSAGE_DUPLICATE_DRUG,
                dataFromFile::toModelType);
    }
}
