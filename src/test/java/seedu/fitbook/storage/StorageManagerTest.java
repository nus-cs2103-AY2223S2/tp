package seedu.fitbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.fitbook.commons.core.GuiSettings;
import seedu.fitbook.model.FitBook;
import seedu.fitbook.model.ReadOnlyFitBook;
import seedu.fitbook.model.UserPrefs;
import seedu.fitbook.storage.routine.JsonFitBookExerciseRoutineStorage;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonFitBookStorage fitBookStorage = new JsonFitBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonFitBookExerciseRoutineStorage fitBookExerciseRoutineStorage =
                new JsonFitBookExerciseRoutineStorage(getTempFilePath("er"));
        storageManager = new StorageManager(fitBookStorage, userPrefsStorage, fitBookExerciseRoutineStorage);
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
    public void fitBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonFitBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonFitBookStorageTest} class.
         */
        FitBook original = getTypicalFitBook();
        storageManager.saveFitBook(original);
        ReadOnlyFitBook retrieved = storageManager.readFitBook().get();
        assertEquals(original, new FitBook(retrieved));
    }

    @Test
    public void getFitBookFilePath() {
        assertNotNull(storageManager.getFitBookFilePath());
    }

}
