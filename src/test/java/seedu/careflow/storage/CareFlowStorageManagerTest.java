package seedu.careflow.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.careflow.testutil.TypicalDrugs.getTypicalDrugInventory;
import static seedu.careflow.testutil.TypicalPatients.getTypicalPatientRecord;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.careflow.commons.core.GuiSettings;
import seedu.careflow.model.DrugInventory;
import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.UserPrefs;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;

class CareFlowStorageManagerTest {

    @TempDir
    public Path testFolder;

    private CareFlowStorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonPatientRecordStorage patientRecordStorage = new JsonPatientRecordStorage(getTempFilePath("pr"));
        JsonDrugInventoryStorage drugInventoryStorage = new JsonDrugInventoryStorage(getTempFilePath("di"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new CareFlowStorageManager(patientRecordStorage, drugInventoryStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6, "DARK"));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void patientRecordReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the CareFlowStorageManager is properly wired to the
         * {@link JsonPatientRecordStorage} class.
         * More extensive testing of PatientRecord saving/reading is done in {@link JsonPatientRecordStorageTest} class.
         */
        PatientRecord original = getTypicalPatientRecord();
        storageManager.savePatientRecord(original);
        ReadOnlyPatientRecord retrieved = storageManager.readPatientRecord().get();
        assertEquals(original, new PatientRecord(retrieved));
    }

    @Test
    public void drugInventoryReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the CareFlowStorageManager is properly wired to the
         * {@link JsonDrugInventoryStorage} class.
         * More extensive testing of DrugInventory saving/reading is done in {@link JsonDrugInventoryStorageTest} class.
         */
        DrugInventory original = getTypicalDrugInventory();
        storageManager.saveDrugInventory(original);
        ReadOnlyDrugInventory retrieved = storageManager.readDrugInventory().get();
        assertEquals(original, new DrugInventory(retrieved));
    }
}
