package ezschedule.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ezschedule.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static ezschedule.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ezschedule.commons.core.GuiSettings;
import ezschedule.model.event.EventContainsKeywordsPredicate;
import ezschedule.testutil.Assert;
import ezschedule.testutil.TypicalPersons;
import ezschedule.testutil.SchedulerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Scheduler(), new Scheduler(modelManager.getScheduler()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setSchedulerFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setSchedulerFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setSchedulerFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setSchedulerFilePath(path);
        assertEquals(path, modelManager.getSchedulerFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.hasEvent(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasEvent(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addEvent(TypicalPersons.ALICE);
        assertTrue(modelManager.hasEvent(TypicalPersons.ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEventList().remove(0));
    }

    @Test
    public void equals() {
        Scheduler scheduler =
                new SchedulerBuilder().withEvent(TypicalPersons.ALICE).withEvent(TypicalPersons.BENSON).build();
        Scheduler differentScheduler = new Scheduler();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(scheduler, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(scheduler, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentScheduler, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = TypicalPersons.ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredEventList(new EventContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(scheduler, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEventList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setSchedulerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(scheduler, differentUserPrefs)));
    }
}
