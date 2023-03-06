package seedu.modtrek.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.modtrek.commons.core.GuiSettings;
import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.ReadOnlyDegreeProgression;
import seedu.modtrek.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonDegreeProgressionStorage addressBookStorage = new JsonDegreeProgressionStorage(getTempFilePath("ab"));
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

    @Test
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonDegreeProgressionStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonDegreeProgressionStorageTest} class.
         */
        DegreeProgression original = getTypicalDegreeProgression();
        storageManager.saveDegreeProgression(original);
        ReadOnlyDegreeProgression retrieved = storageManager.readDegreeProgression().get();
        assertEquals(original, new DegreeProgression(retrieved));
    }

    @Test
    public void getDegreeProgressionFilePath() {
        assertNotNull(storageManager.getDegreeProgressionFilePath());
    }

}
