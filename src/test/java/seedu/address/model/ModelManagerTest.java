package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.APPLE;
import static seedu.address.testutil.TypicalInternships.GOOGLE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.internship.InternshipContainsKeywordsPredicate;
import seedu.address.testutil.InternBuddyBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new InternBuddy(), new InternBuddy(modelManager.getInternBuddy()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setInternBuddyFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setInternBuddyFilePath(Paths.get("new/address/book/file/path"));
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
    public void setInternBuddyFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setInternBuddyFilePath(null));
    }

    @Test
    public void setInternBuddyFilePath_validPath_setsInternBuddyFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setInternBuddyFilePath(path);
        assertEquals(path, modelManager.getInternBuddyFilePath());
    }

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInInternBuddy_returnsFalse() {
        assertFalse(modelManager.hasInternship(APPLE));
    }

    @Test
    public void hasInternship_internshipInInternBuddy_returnsTrue() {
        modelManager.addInternship(APPLE);
        assertTrue(modelManager.hasInternship(APPLE));
    }

    @Test
    public void getFilteredInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager
                .getFilteredInternshipList().remove(0));
    }

    @Test
    public void equals() {
        InternBuddy internBuddy = new InternBuddyBuilder().withInternship(APPLE).withInternship(GOOGLE).build();
        InternBuddy differentInternBuddy = new InternBuddy();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(internBuddy, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(internBuddy, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different internBuddy -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentInternBuddy, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = APPLE.getCompanyName().fullCompanyName.split("\\s+");
        modelManager.updateFilteredInternshipList(new InternshipContainsKeywordsPredicate(Arrays.asList(keywords),
                Collections.emptyList(),
                Collections.emptyList()));
        assertFalse(modelManager.equals(new ModelManager(internBuddy, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setInternBuddyFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(internBuddy, differentUserPrefs)));
    }
}
