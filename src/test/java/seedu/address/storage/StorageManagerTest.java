package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalModules.getTypicalModuleTracker;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ModuleTracker;
import seedu.address.model.ReadOnlyModuleTracker;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonModuleTrackerStorage moduleTrackerStorage = new JsonModuleTrackerStorage(getTempFilePath("mt"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(moduleTrackerStorage, userPrefsStorage);
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
    public void moduleTrackerReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonModuleTrackerStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonModuleTrackerStorageTest} class.
         */
        ModuleTracker original = getTypicalModuleTracker();
        storageManager.saveModuleTracker(original);
        ReadOnlyModuleTracker retrieved = storageManager.readModuleTracker().get();
        assertEquals(original, new ModuleTracker(retrieved));
    }

    @Test
    public void getModuleTrackerFilePath() {
        assertNotNull(storageManager.getModuleTrackerFilePath());
    }

}
