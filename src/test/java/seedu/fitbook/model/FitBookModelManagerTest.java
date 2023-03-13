package seedu.fitbook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.model.FitBookModel.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.fitbook.testutil.Assert.assertThrows;
import static seedu.fitbook.testutil.TypicalClients.ALICE;
import static seedu.fitbook.testutil.TypicalClients.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.fitbook.commons.core.GuiSettings;
import seedu.fitbook.model.client.FindContainsKeywordsPredicate;
import seedu.fitbook.testutil.FitBookBuilder;

public class FitBookModelManagerTest {

    private FitBookModelManager modelManager = new FitBookModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FitBook(), new FitBook(modelManager.getFitBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFitBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFitBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setFitBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFitBookFilePath(null));
    }

    @Test
    public void setFitBookFilePath_validPath_setsFitBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setFitBookFilePath(path);
        assertEquals(path, modelManager.getFitBookFilePath());
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInFitBook_returnsFalse() {
        assertFalse(modelManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInFitBook_returnsTrue() {
        modelManager.addClient(ALICE);
        assertTrue(modelManager.hasClient(ALICE));
    }

    @Test
    public void getFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredClientList().remove(0));
    }

    @Test
    public void equals() {
        FitBook fitBook = new FitBookBuilder().withClient(ALICE).withClient(BENSON).build();
        FitBook differentFitBook = new FitBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new FitBookModelManager(fitBook, userPrefs);
        FitBookModelManager modelManagerCopy = new FitBookModelManager(fitBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different fitBook -> returns false
        assertFalse(modelManager.equals(new FitBookModelManager(differentFitBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredClientList(new FindContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new FitBookModelManager(fitBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredClientList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFitBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new FitBookModelManager(fitBook, differentUserPrefs)));
    }
}
