package seedu.vms.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.vms.testutil.TypicalPatients.getTypicalPatientManager;

import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.vms.commons.core.GuiSettings;
import seedu.vms.model.UserPrefs;
import seedu.vms.model.patient.PatientManager;
import seedu.vms.model.patient.ReadOnlyPatientManager;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.storage.vaccination.JsonVaxTypeStorage;
import seedu.vms.testutil.SampleVaxTypeData;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonPatientManagerStorage patientManagerStorage = new JsonPatientManagerStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonVaxTypeStorage vaxTypeStorage = new JsonVaxTypeStorage();
        storageManager = new StorageManager(patientManagerStorage, vaxTypeStorage, userPrefsStorage);
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
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs();
        assertEquals(original, retrieved);
    }

    @Test
    public void patientManagerReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonPatientManagerStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonPatientManagerStorageTest} class.
         */
        PatientManager original = getTypicalPatientManager();
        storageManager.savePatientManager(original);
        ReadOnlyPatientManager retrieved = storageManager.readPatientManager();
        assertEquals(original, new PatientManager(retrieved));
    }

    @Test
    public void getPatientManagerFilePath() {
        assertNotNull(storageManager.getPatientManagerFilePath());
    }

    @Test
    public void loadDefaultVaxTypes() {
        /*
         * This is an integration test to verify that VaxTypeManager has been integrated properly.
         */
        Optional<VaxType> data = storageManager
                .loadDefaultVaxTypes()
                .get(SampleVaxTypeData.NAME_REAL.getName());
        assertTrue(data.isPresent());
    }

}
