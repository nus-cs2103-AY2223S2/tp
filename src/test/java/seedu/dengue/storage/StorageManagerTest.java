package seedu.dengue.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.dengue.commons.core.GuiSettings;
import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;
import seedu.dengue.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonDengueHotspotStorage dengueHotspotTrackerStorage = new JsonDengueHotspotStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(dengueHotspotTrackerStorage, userPrefsStorage);
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

    @Test
    public void dengueHotspotTrackerReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonDengueHotspotTrackerStorage} class.
         * More extensive testing of UserPref saving/reading is done in
         * {@link JsonDengueHotspotTrackerStorageTest} class.
         */
        DengueHotspotTracker original = getTypicalDengueHotspotTracker();
        storageManager.saveDengueHotspotTracker(original);
        ReadOnlyDengueHotspotTracker retrieved = storageManager.readDengueHotspotTracker().get();
        assertEquals(original, new DengueHotspotTracker(retrieved));
    }

    @Test
    public void getDengueHotspotTrackerFilePath() {
        assertNotNull(storageManager.getDengueHotspotTrackerFilePath());
    }

}
