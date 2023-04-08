package ezschedule.model;

import static ezschedule.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static ezschedule.testutil.Assert.assertThrows;
import static ezschedule.testutil.TypicalEvents.ART;
import static ezschedule.testutil.TypicalEvents.BOAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ezschedule.commons.core.GuiSettings;
import ezschedule.model.event.EventContainsKeywordsPredicate;
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
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setSchedulerFilePath(Paths.get("scheduler/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setSchedulerFilePath(Paths.get("new/scheduler/file/path"));
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
    public void setSchedulerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setSchedulerFilePath(null));
    }

    @Test
    public void setSchedulerFilePath_validPath_setsSchedulerFilePath() {
        Path path = Paths.get("scheduler/file/path");
        modelManager.setSchedulerFilePath(path);
        assertEquals(path, modelManager.getSchedulerFilePath());
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInScheduler_returnsFalse() {
        assertFalse(modelManager.hasEvent(ART));
    }

    @Test
    public void hasEvent_eventInScheduler_returnsTrue() {
        modelManager.addEvent(ART);
        assertTrue(modelManager.hasEvent(ART));
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getEventList().remove(0));
    }

    @Test
    public void getFilteredEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEventList().remove(0));
    }

    @Test
    public void getUpcomingEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getUpcomingEventList().remove(0));
    }

    @Test
    public void getFindEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFindEventList().remove(0));
    }

    @Test
    public void equals() {
        Scheduler scheduler =
                new SchedulerBuilder().withEvent(ART).withEvent(BOAT).build();
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

        // different scheduler -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentScheduler, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ART.getName().fullName.split("\\s+");
        modelManager.updateFilteredEventList(new EventContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(scheduler, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setSchedulerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(scheduler, differentUserPrefs)));
    }
}
