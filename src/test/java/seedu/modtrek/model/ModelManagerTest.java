package seedu.modtrek.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.Assert.assertThrows;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;
import static seedu.modtrek.testutil.TypicalModules.MA2002;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.modtrek.commons.core.GuiSettings;
import seedu.modtrek.testutil.DegreeProgressionBuilder;


public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new DegreeProgression(), new DegreeProgression(modelManager.getDegreeProgression()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFilePath(Paths.get("new/address/book/file/path"));
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
    public void setDegreeProgressionFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setDegreeProgressionFilePath(null));
    }

    @Test
    public void setDegreeProgressionFilePath_validPath_setsDegreeProgressionFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setDegreeProgressionFilePath(path);
        assertEquals(path, modelManager.getDegreeProgressionFilePath());
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasModule(null));
    }

    @Test
    public void hasModule_personNotInDegreeProgression_returnsFalse() {
        assertFalse(modelManager.hasModule(CS1101S));
    }

    @Test
    public void hasModule_personInDegreeProgression_returnsTrue() {
        modelManager.addModule(CS1101S);
        assertTrue(modelManager.hasModule(CS1101S));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }

    @Test
    public void equals() {
        DegreeProgression degreeProgression = new DegreeProgressionBuilder().withModule(CS1101S)
                .withModule(MA2002).build();
        DegreeProgression differentDegreeProgression = new DegreeProgression();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(degreeProgression, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(degreeProgression, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different degreeProgression -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentDegreeProgression, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredModuleList(modelManager.getPredicate());

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(degreeProgression, differentUserPrefs)));
    }
}
