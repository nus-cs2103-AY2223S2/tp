package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ENTITIES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntities.LEEROY;
import static seedu.address.testutil.TypicalEntities.RAT;
import static seedu.address.testutil.TypicalEntities.SPOON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.entity.NameContainsKeywordsPredicate;
import seedu.address.testutil.RerollBuilder;

class ModelManagerTest {
    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Reroll(), new Reroll(modelManager.getReroll()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setRerollFilePath(Paths.get("fake/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setRerollFilePath(Paths.get("other/fake/path"));
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
    public void setRerollFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setRerollFilePath(null));
    }

    @Test
    public void setRerollFilePath_validPath_setsRerollFilePath() {
        Path path = Paths.get("reroll/file/path");
        modelManager.setRerollFilePath(path);
        assertEquals(path, modelManager.getRerollFilePath());
    }

    @Test
    public void hasEntity_nullEntity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEntity(null));
    }

    @Test
    public void hasEntity_entityNotInReroll_returnsFalse() {
        assertFalse(modelManager.hasEntity(RAT));
    }

    @Test
    public void hasEntity_entityInReroll_returnsTrue() {
        modelManager.addEntity(RAT);
        assertTrue(modelManager.hasEntity(RAT));
    }

    @Test
    public void getFilteredEntityList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEntityList().remove(0));
    }

    @Test
    public void equals() {
        Reroll reroll = new RerollBuilder().withEntity(LEEROY).withEntity(SPOON).build();
        Reroll otherReroll = new Reroll();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(reroll, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(reroll, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(otherReroll, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = LEEROY.getName().fullName.split("\\s+");
        modelManager.updateFilteredEntityList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(reroll, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEntityList(PREDICATE_SHOW_ALL_ENTITIES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setRerollFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(reroll, differentUserPrefs)));

    }
}
