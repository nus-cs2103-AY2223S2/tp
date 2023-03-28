package seedu.internship.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternBuddy;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.internship.commons.core.GuiSettings;
import seedu.internship.model.InternBuddy;
import seedu.internship.model.ReadOnlyInternBuddy;
import seedu.internship.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonInternBuddyStorage internBuddyStorage = new JsonInternBuddyStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(internBuddyStorage, userPrefsStorage);
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
    public void internBuddyReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonInternBuddyStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonInternBuddyStorageTest} class.
         */
        InternBuddy original = getTypicalInternBuddy();
        storageManager.saveInternBuddy(original);
        ReadOnlyInternBuddy retrieved = storageManager.readInternBuddy().get();
        assertEquals(original, new InternBuddy(retrieved));
    }

    @Test
    public void getInternBuddyFilePath() {
        assertNotNull(storageManager.getInternBuddyFilePath());
    }

}
