package seedu.task.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.task.testutil.TypicalDailyPlans.getTypicalPlanner;
import static seedu.task.testutil.TypicalTasks.getTypicalTaskBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.task.commons.core.GuiSettings;
import seedu.task.model.Planner;
import seedu.task.model.ReadOnlyPlanner;
import seedu.task.model.ReadOnlyTaskBook;
import seedu.task.model.TaskBook;
import seedu.task.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTaskBookStorage taskBookStorage = new JsonTaskBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonPlannerStorage plannerStorage = new JsonPlannerStorage(getTempFilePath("plans"));
        storageManager = new StorageManager(taskBookStorage, userPrefsStorage, plannerStorage);
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

    //@@author lywich-reused
    @Test
    public void taskBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTaskBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonTaskBookStorageTest} class.
         */
        TaskBook original = getTypicalTaskBook();
        storageManager.saveTaskBook(original);
        ReadOnlyTaskBook retrieved = storageManager.readTaskBook().get();
        assertEquals(original, new TaskBook(retrieved));
    }

    //@@author joyngjr
    @Test
    public void plannerReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonPlannerStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonPlannerStorageTest} class.
         */
        Planner original = getTypicalPlanner();
        storageManager.savePlanner(original);
        ReadOnlyPlanner retrieved = storageManager.readPlanner().get();
        assertEquals(original, new Planner(retrieved));
    }


    //@@author
    @Test
    public void getTaskBookFilePath() {
        assertNotNull(storageManager.getTaskBookFilePath());
    }

}
