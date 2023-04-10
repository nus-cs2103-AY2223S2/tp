package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.TaskBookModel.PREDICATE_SHOW_ALL_TASKS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ONE;
import static seedu.address.testutil.TypicalTasks.TWO;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.task.TaskDescriptionContainsKeywordsPredicate;
import seedu.address.testutil.TaskBookBuilder;

public class TaskBookModelManagerTest {

    private TaskBookModelManager taskBookModelManager = new TaskBookModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), taskBookModelManager.getUserPrefs());
        assertEquals(new GuiSettings(), taskBookModelManager.getGuiSettings());
        assertEquals(new TaskBook(), new TaskBook(taskBookModelManager.getTaskBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskBookModelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        taskBookModelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, taskBookModelManager.getUserPrefs());

        // Modifying userPrefs should not modify taskBookModelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTaskBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, taskBookModelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskBookModelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        taskBookModelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, taskBookModelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskBookModelManager.setTaskBookFilePath(null));
    }

    @Test
    public void setTaskBookFilePath_validPath_setsTaskBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        taskBookModelManager.setTaskBookFilePath(path);
        assertEquals(path, taskBookModelManager.getTaskBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskBookModelManager.hasTask(null));
    }

    @Test
    public void hasPerson_personNotInTaskBook_returnsFalse() {
        assertFalse(taskBookModelManager.hasTask(ONE));
    }

    @Test
    public void hasPerson_personInTaskBook_returnsTrue() {
        taskBookModelManager.addTask(ONE);
        assertTrue(taskBookModelManager.hasTask(ONE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskBookModelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void equals() {
        TaskBook taskBook = new TaskBookBuilder().withTask(ONE).withTask(TWO).build();
        TaskBook differentTaskBook = new TaskBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        taskBookModelManager = new TaskBookModelManager(taskBook, userPrefs);
        TaskBookModelManager taskBookModelManagerCopy = new TaskBookModelManager(taskBook, userPrefs);
        assertTrue(taskBookModelManager.equals(taskBookModelManagerCopy));

        // same object -> returns true
        assertTrue(taskBookModelManager.equals(taskBookModelManager));

        // null -> returns false
        assertFalse(taskBookModelManager.equals(null));

        // different types -> returns false
        assertFalse(taskBookModelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(taskBookModelManager.equals(new TaskBookModelManager(differentTaskBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ONE.getDescription().fullTaskDescription.split("\\s+");
        taskBookModelManager.updateFilteredTaskList(new
            TaskDescriptionContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(taskBookModelManager.equals(new TaskBookModelManager(taskBook, userPrefs)));

        // resets taskBookModelManager to initial state for upcoming tests
        taskBookModelManager.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

    }
}
