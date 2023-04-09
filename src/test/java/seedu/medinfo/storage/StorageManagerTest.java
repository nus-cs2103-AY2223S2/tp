package seedu.medinfo.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.medinfo.commons.core.GuiSettings;
import seedu.medinfo.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonMedInfoStorage addressBookStorage = new JsonMedInfoStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
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
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    //    @Test
    //    public void medInfoReadSave() throws Exception {
    //        /*
    //         * Note: This is an integration test that verifies the StorageManager is properly wired to the
    //         * {@link JsonMedInfoStorage} class.
    //         * More extensive testing of UserPref saving/reading is done in {@link JsonMedInfoStorageTest} class.
    //         */
    //        MedInfo original = getTypicalMedInfo();
    //        storageManager.saveMedInfo(original);
    //        ReadOnlyMedInfo retrieved = storageManager.readMedInfo().get();
    //        assertEquals(original, new MedInfo(retrieved));
    //    }

    @Test
    public void getMedInfoFilePath() {
        assertNotNull(storageManager.getMedInfoFilePath());
    }

}
