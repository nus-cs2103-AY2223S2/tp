package ezschedule.storage;

import static ezschedule.testutil.TypicalEvents.getTypicalScheduler;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ezschedule.commons.core.GuiSettings;
import ezschedule.model.ReadOnlyScheduler;
import ezschedule.model.Scheduler;
import ezschedule.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonSchedulerStorage schedulerStorage = new JsonSchedulerStorage(getTempFilePath("sc"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(schedulerStorage, userPrefsStorage);
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
    public void schedulerBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonSchedulerStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonSchedulerStorageTest} class.
         */

        Scheduler original = getTypicalScheduler();
        storageManager.saveScheduler(original);
        ReadOnlyScheduler retrieved = storageManager.readScheduler().get();
        assertEquals(original, new Scheduler(retrieved));
    }

    @Test
    public void getSchedulerFilePath() {
        assertNotNull(storageManager.getSchedulerFilePath());
    }
}
