package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalListings.CHICKEN_RICE_UNCLE;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ListingBook(), new ListingBook(modelManager.getListingBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setListingBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setListingBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setListingBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setListingBookFilePath(null));
    }

    @Test
    public void setListingBookFilePath_validPath_setsListingBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setListingBookFilePath(path);
        assertEquals(path, modelManager.getListingBookFilePath());
    }

    @Test
    public void hasListing_nullListing_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasListing(null));
    }

    @Test
    public void hasListing_personNotInListingBook_returnsFalse() {
        assertFalse(modelManager.hasListing(CHICKEN_RICE_UNCLE));
    }

    @Test
    public void hasListing_listingInListingBook_returnsTrue() {
        modelManager.addListing(CHICKEN_RICE_UNCLE);
        assertTrue(modelManager.hasListing(CHICKEN_RICE_UNCLE));
    }

    @Test
    public void getDisplayedListingList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getDisplayedListingBook().remove(0));
    }

    @Test
    public void hasPreviousState_noPreviousState_returnsFalse() {
        ModelManager newModelManager = new ModelManager();
        assertFalse(newModelManager.hasPreviousState());
    }

    @Test
    public void hasPreviousState_hasPreviousState_returnsTrue() {
        ModelManager newModelManager = new ModelManager();
        newModelManager.addListing(CHICKEN_RICE_UNCLE);
        assertTrue(newModelManager.hasPreviousState());
    }
}
