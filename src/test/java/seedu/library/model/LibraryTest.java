package seedu.library.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.logic.commands.CommandTestUtil.VALID_AUTHOR_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.library.testutil.Assert.assertThrows;
import static seedu.library.testutil.TypicalBookmarks.AOT;
import static seedu.library.testutil.TypicalBookmarks.getTypicalLibrary;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.library.model.bookmark.Bookmark;
import seedu.library.model.bookmark.exceptions.DuplicateBookmarkException;
import seedu.library.testutil.BookmarkBuilder;

public class LibraryTest {

    private final Library library = new Library();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), library.getBookmarkList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> library.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyLibrary_replacesData() {
        Library newData = getTypicalLibrary();
        library.resetData(newData);
        assertEquals(newData, library);
    }

    @Test
    public void resetData_withDuplicateBookmarks_throwsDuplicateBookmarkException() {
        // Two Bookmarks with the same identity fields
        Bookmark editedAlice = new BookmarkBuilder(AOT).withAuthor(VALID_AUTHOR_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Bookmark> newBookmarks = Arrays.asList(AOT, editedAlice);
        LibraryStub newData = new LibraryStub(newBookmarks);

        assertThrows(DuplicateBookmarkException.class, () -> library.resetData(newData));
    }

    @Test
    public void hasBookmark_nullBookmark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> library.hasBookmark(null));
    }

    @Test
    public void hasBookmark_bookmarkNotInLibrary_returnsFalse() {
        assertFalse(library.hasBookmark(AOT));
    }

    @Test
    public void hasBookmark_bookmarkInLibrary_returnsTrue() {
        library.addBookmark(AOT);
        assertTrue(library.hasBookmark(AOT));
    }

    @Test
    public void hasBookmark_bookmarkWithSameIdentityFieldsInLibrary_returnsTrue() {
        library.addBookmark(AOT);
        Bookmark editedAlice = new BookmarkBuilder(AOT).withAuthor(VALID_AUTHOR_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(library.hasBookmark(editedAlice));
    }

    @Test
    public void getBookmarkList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> library.getBookmarkList().remove(0));
    }

    /**
     * A stub ReadOnlyLibrary whose Bookmarks list can violate interface constraints.
     */
    private static class LibraryStub implements ReadOnlyLibrary {
        private final ObservableList<Bookmark> bookmarks = FXCollections.observableArrayList();

        LibraryStub(Collection<Bookmark> bookmarks) {
            this.bookmarks.setAll(bookmarks);
        }

        @Override
        public ObservableList<Bookmark> getBookmarkList() {
            return bookmarks;
        }
    }

}
