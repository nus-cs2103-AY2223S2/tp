package trackr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static trackr.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalPersons.ALICE;
import static trackr.testutil.TypicalPersons.BENSON;
import static trackr.testutil.TypicalTasks.BUY_FLOUR_N;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import trackr.commons.core.GuiSettings;
import trackr.model.person.NameContainsKeywordsPredicate;
import trackr.model.task.TaskContainsKeywordsPredicate;
import trackr.testutil.AddressBookBuilder;
import trackr.testutil.TaskListBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
        assertEquals(new TaskList(), new TaskList(modelManager.getTaskList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTrackrFilePath(Paths.get("trackr/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTrackrFilePath(Paths.get("new/trackr/file/path"));
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
    public void setTrackrFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTrackrFilePath(null));
    }

    @Test
    public void setTrackrFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("trackr/file/path");
        modelManager.setTrackrFilePath(path);
        assertEquals(path, modelManager.getTrackrFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskList_returnsFalse() {
        assertFalse(modelManager.hasTask(SORT_INVENTORY_N));
    }

    @Test
    public void hasTask_taskInTaskList_returnsTrue() {
        modelManager.addTask(SORT_INVENTORY_N);
        assertTrue(modelManager.hasTask(SORT_INVENTORY_N));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        TaskList taskList = new TaskListBuilder().withTask(SORT_INVENTORY_N).withTask(BUY_FLOUR_N).build();
        TaskList differentTaskList = new TaskList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, taskList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, taskList, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, taskList, userPrefs)));

        // different taskList -> returns false
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentTaskList, userPrefs)));

        // different filteredPersonList -> returns false
        String[] personKeywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(personKeywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, taskList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different filteredTaskList -> returns false
        String[] taskKeywords = SORT_INVENTORY_N.getTaskName().fullTaskName.split("\\s+");
        TaskContainsKeywordsPredicate sortPredicate = new TaskContainsKeywordsPredicate();
        sortPredicate.setTaskNameKeywords(Arrays.asList(taskKeywords));
        modelManager.updateFilteredTaskList(sortPredicate);
        assertFalse(modelManager.equals(new ModelManager(addressBook, taskList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        // different addressBook and different taskList -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, differentTaskList, userPrefs)));

        // different filteredPersonList and different filteredTaskList -> returns false
        personKeywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(personKeywords)));
        taskKeywords = BUY_FLOUR_N.getTaskName().fullTaskName.split("\\s+");
        TaskContainsKeywordsPredicate buyPredicate = new TaskContainsKeywordsPredicate();
        buyPredicate.setTaskNameKeywords(Arrays.asList(taskKeywords));
        modelManager.updateFilteredTaskList(buyPredicate);
        assertFalse(modelManager.equals(new ModelManager(addressBook, taskList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        modelManager.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTrackrFilePath(Paths.get("differentFilePath"));

        assertFalse(modelManager.equals(new ModelManager(addressBook, taskList, differentUserPrefs)));
    }
}
