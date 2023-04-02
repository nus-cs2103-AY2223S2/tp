package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2103T_LEC;
import static seedu.address.testutil.TypicalModules.CS2106_TUT;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.NameContainsKeywordsPredicate;
import seedu.address.testutil.ModuleTrackerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ModuleTracker(), new ModuleTracker(modelManager.getModuleTracker()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setModuleTrackerFilePath(Paths.get("module/tracker/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setModuleTrackerFilePath(Paths.get("new/module/tracker/file/path"));
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
    public void setModuleTrackerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setModuleTrackerFilePath(null));
    }

    @Test
    public void setModuleTrackerFilePath_validPath_setsModuleTrackerFilePath() {
        Path path = Paths.get("module/tracker/file/path");
        modelManager.setModuleTrackerFilePath(path);
        assertEquals(path, modelManager.getModuleTrackerFilePath());
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInModuleTracker_returnsFalse() {
        assertFalse(modelManager.hasModule(CS2106_TUT));
    }

    @Test
    public void hasModule_moduleInModuleTracker_returnsTrue() {
        modelManager.addModule(CS2106_TUT);
        assertTrue(modelManager.hasModule(CS2106_TUT));
    }

    @Test
    public void getDisplayedModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getDisplayedModuleList().remove(0));
    }

    @Test
    public void equals() {
        ModuleTracker moduleTracker = new ModuleTrackerBuilder()
                .withModule(CS2106_TUT).withModule(CS2103T_LEC).build();
        ModuleTracker differentModuleTracker = new ModuleTracker();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(moduleTracker, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(moduleTracker, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different moduleTracker -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentModuleTracker, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = CS2106_TUT.getName().fullName.split("\\s+");
        modelManager.updateFilteredModuleList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(moduleTracker, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setModuleTrackerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(moduleTracker, differentUserPrefs)));
    }
}
