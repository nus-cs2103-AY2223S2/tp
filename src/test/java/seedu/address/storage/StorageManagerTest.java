package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.EduMate;
import seedu.address.model.ReadOnlyEduMate;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.PersonUtil;

public class StorageManagerTest {

    private static final String STORAGE_FILE_PATH = "ab";
    private static final String USER_PREFS_FILE_PATH = "prefs";

    private static final String HISTORY_FILE_PATH = "cd";

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        EduMateStorageManager eduMateStorage = new EduMateStorageManager(getTempFilePath(
                STORAGE_FILE_PATH), getTempFilePath(HISTORY_FILE_PATH));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath(
                USER_PREFS_FILE_PATH));
        storageManager = new StorageManager(eduMateStorage, userPrefsStorage);
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
    public void eduMateReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link EduMateStorageManager} class.
         * More extensive testing of UserPref saving/reading is done in {@link EduMateStorageManagerTest} class.
         */
        EduMate original = getTypicalEduMate();
        storageManager.saveEduMate(original);
        ReadOnlyEduMate retrieved = storageManager.readEduMate().get();
        ReadOnlyEduMate pushed = new EduMate(retrieved);
        assertEquals(original.getPersonList(), pushed.getPersonList());
        assertTrue(PersonUtil.isSameUserAndUserStub(pushed.getUser(), original.getUser()));
    }

    @Test
    public void getEduMateFilePath() {
        assertNotNull(storageManager.getEduMateFilePath());
    }

    @Test
    public void getUserPrefsFilePath_validFilePath_success() {
        assertEquals(storageManager.getUserPrefsFilePath(),
                getTempFilePath(USER_PREFS_FILE_PATH));
    }

}
