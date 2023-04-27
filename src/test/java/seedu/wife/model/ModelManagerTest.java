package seedu.wife.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.model.Model.PREDICATE_SHOW_ALL_FOODS;
import static seedu.wife.testutil.Assert.assertThrows;
import static seedu.wife.testutil.TypicalFood.CHOCOLATE;
import static seedu.wife.testutil.TypicalFood.MEIJI;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.wife.commons.core.GuiSettings;
import seedu.wife.model.food.NameContainsKeywordsPredicate;
import seedu.wife.testutil.WifeBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Wife(), new Wife(modelManager.getWife()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setWifeFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setWifeFilePath(Paths.get("new/address/book/file/path"));
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
    public void setWifeFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setWifeFilePath(null));
    }

    @Test
    public void setWifePath_validPath_setsWifeFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setWifeFilePath(path);
        assertEquals(path, modelManager.getWifeFilePath());
    }

    @Test
    public void hasFood_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasFood(null));
    }

    @Test
    public void hasFood_foodNotInWife_returnsFalse() {
        assertFalse(modelManager.hasFood(MEIJI));
    }

    @Test
    public void hasFood_foodInWife_returnsTrue() {
        modelManager.addFood(MEIJI);
        assertTrue(modelManager.hasFood(MEIJI));
    }

    @Test
    public void getFilteredFoodList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredFoodList().remove(0));
    }

    @Test
    public void equals() {
        Wife wife = new WifeBuilder().withFood(MEIJI).withFood(CHOCOLATE).build();
        Wife differentWife = new Wife();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(wife, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(wife, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentWife, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = MEIJI.getName().toString().split("\\s+");
        modelManager.updateFilteredFoodList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(wife, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setWifeFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(wife, differentUserPrefs)));
    }
}
