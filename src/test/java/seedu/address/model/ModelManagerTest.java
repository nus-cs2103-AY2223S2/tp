package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.NameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalVideos;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Tracker(), modelManager.getTracker());
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTrackerFilePath(Paths.get("tracker/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTrackerFilePath(Paths.get("new/tracker/file/path"));
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
    public void setTrackerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTrackerFilePath(null));
    }

    @Test
    public void setTrackerFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("tracker/file/path");
        modelManager.setTrackerFilePath(path);
        assertEquals(path, modelManager.getTrackerFilePath());
    }

    @Test
    public void hasLecture_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.hasLecture(null, TypicalLectures.CS2040S_WEEK_1));
    }

    @Test
    public void deleteLecture_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.deleteLecture(null, TypicalLectures.CS2040S_WEEK_1));
    }

    @Test
    public void addLecture_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addLecture(null, TypicalLectures.CS2040S_WEEK_1));
    }

    @Test
    public void setLecture_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.setLecture(null, TypicalLectures.ST2334_TOPIC_1, TypicalLectures.ST2334_TOPIC_1));
    }

    @Test
    public void hasVideo_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasVideo(null, TypicalVideos.CONTENT_VIDEO));
    }

    @Test
    public void deleteVideo_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteVideo(null, TypicalVideos.CONTENT_VIDEO));
    }

    @Test
    public void addVideo_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addVideo(null, TypicalVideos.CONTENT_VIDEO));
    }

    @Test
    public void setVideo_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.setVideo(null, TypicalVideos.CONTENT_VIDEO, TypicalVideos.CONTENT_VIDEO));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }

    @Test
    public void equals() {
        Tracker tracker = TypicalModules.getTypicalTracker();
        Tracker differentTracker = new Tracker();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(tracker, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(tracker, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different tracker -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTracker, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = new String[] {"Data"};
        modelManager.updateFilteredModuleList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(tracker, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTrackerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(tracker, differentUserPrefs)));
    }
}
