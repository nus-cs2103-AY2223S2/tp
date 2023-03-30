package seedu.quickcontacts.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.quickcontacts.testutil.TypicalQuickBooks.getTypicalQuickBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.quickcontacts.commons.core.GuiSettings;
import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.model.ReadOnlyQuickBook;
import seedu.quickcontacts.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonQuickBookStorage quickBookStorage = new JsonQuickBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(quickBookStorage, userPrefsStorage);
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
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6, false));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void quickBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonQuickBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonQuickBookStorageTest} class.
         */
        QuickBook original = getTypicalQuickBook();
        storageManager.saveQuickBook(original);
        ReadOnlyQuickBook retrieved = storageManager.readQuickBook().get();
        assertEquals(original, new QuickBook(retrieved));
    }

    @Test
    public void getQuickBookFilePath() {
        assertNotNull(storageManager.getQuickBookFilePath());
    }

}
