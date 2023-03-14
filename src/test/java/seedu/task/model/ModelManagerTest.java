package seedu.task.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalDailyPlans.getTypicalDailyPlans;
import static seedu.task.testutil.TypicalTasks.ALICE;
import static seedu.task.testutil.TypicalTasks.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.task.commons.core.GuiSettings;
import seedu.task.model.task.NameContainsKeywordsPredicate;
import seedu.task.testutil.TaskBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TaskBook(), new TaskBook(modelManager.getTaskBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTaskBookFilePath(Paths.get("task/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTaskBookFilePath(Paths.get("new/task/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setTaskBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTaskBookFilePath(null));
    }

    @Test
    public void setTaskBookFilePath_validPath_setsTaskBookFilePath() {
        Path path = Paths.get("task/book/file/path");
        modelManager.setTaskBookFilePath(path);
        assertEquals(path, modelManager.getTaskBookFilePath());
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskBook_returnsFalse() {
        assertFalse(modelManager.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskInTaskBook_returnsTrue() {
        modelManager.addTask(ALICE);
        assertTrue(modelManager.hasTask(ALICE));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void equals() {
        TaskBook taskBook = new TaskBookBuilder().withTask(ALICE).withTask(BENSON).build();
        TaskBook differentTaskBook = new TaskBook();
        UserPrefs userPrefs = new UserPrefs();
        Planner samePlanner = getTypicalDailyPlans();

        // same values -> returns true
        modelManager = new ModelManager(taskBook, userPrefs, samePlanner);
        ModelManager modelManagerCopy = new ModelManager(taskBook, userPrefs, samePlanner);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different taskBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTaskBook, userPrefs, samePlanner)));

        // different filteredList -> returns false
        String keyphrase = ALICE.getName().fullName;
        modelManager.updateFilteredTaskList(new NameContainsKeywordsPredicate(keyphrase));
        assertFalse(modelManager.equals(new ModelManager(taskBook, userPrefs, samePlanner)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTaskBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(taskBook, differentUserPrefs, samePlanner)));
    }
}
