package seedu.library.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.model.Model.PREDICATE_SHOW_ALL_BOOKMARKS;
import static seedu.library.testutil.Assert.assertThrows;
import static seedu.library.testutil.TypicalBookmarks.AOT;
import static seedu.library.testutil.TypicalBookmarks.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.library.commons.core.GuiSettings;
import seedu.library.model.bookmark.BookmarkContainsKeywordsPredicate;
import seedu.library.testutil.LibraryBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Library(), new Library(modelManager.getLibrary()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setLibraryFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setLibraryFilePath(Paths.get("new/address/book/file/path"));
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
    public void setLibraryFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setLibraryFilePath(null));
    }

    @Test
    public void setLibraryFilePath_validPath_setsLibraryFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setLibraryFilePath(path);
        assertEquals(path, modelManager.getLibraryFilePath());
    }

    @Test
    public void hasBookmark_nullBookmark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasBookmark(null));
    }

    @Test
    public void hasBookmark_bookmarkNotInLibrary_returnsFalse() {
        assertFalse(modelManager.hasBookmark(AOT));
    }

    @Test
    public void hasBookmark_bookmarkInLibrary_returnsTrue() {
        modelManager.addBookmark(AOT);
        assertTrue(modelManager.hasBookmark(AOT));
    }

    @Test
    public void getFilteredBookmarkList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredBookmarkList().remove(0));
    }

    @Test
    public void equals() {
        Library library = new LibraryBuilder().withBookmark(AOT).withBookmark(BENSON).build();
        Library differentLibrary = new Library();
        UserPrefs userPrefs = new UserPrefs();
        Tags tags = new Tags();

        // same values -> returns true
        modelManager = new ModelManager(library, userPrefs, tags);
        ModelManager modelManagerCopy = new ModelManager(library, userPrefs, tags);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different Library -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentLibrary, userPrefs, tags)));

        // different filteredList -> returns false
        String[] keywords = AOT.getTitle().value.split("\\s+");
        modelManager.updateFilteredBookmarkList(
                new BookmarkContainsKeywordsPredicate(Arrays.asList(keywords), null, null, null));
        assertFalse(modelManager.equals(new ModelManager(library, userPrefs, tags)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredBookmarkList(PREDICATE_SHOW_ALL_BOOKMARKS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setLibraryFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(library, differentUserPrefs, tags)));
    }

    @Test
    public void getSelectedBookmark_default_success() {
        assertEquals(modelManager.getSelectedBookmark(), null);
    }
    @Test
    public void getSelectedIndex_default_success() {
        assertEquals(modelManager.getSelectedIndex(), -1);
    }
    @Test
    public void getUpdatedBookmark_success() {
        modelManager.updateSelectedBookmark(AOT);
        assertEquals(modelManager.getSelectedBookmark(), AOT);
    }
    @Test
    public void getUpdatedIndex_success() {
        modelManager.updateSelectedIndex(1);
        assertEquals(modelManager.getSelectedIndex(), 1);
    }
}
