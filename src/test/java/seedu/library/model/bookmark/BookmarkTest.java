package seedu.library.model.bookmark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.logic.commands.CommandTestUtil.VALID_AUTHOR_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_GENRE_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_PROGRESS_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static seedu.library.testutil.Assert.assertThrows;
import static seedu.library.testutil.TypicalBookmarks.AOT;
import static seedu.library.testutil.TypicalBookmarks.AOTString;
import static seedu.library.testutil.TypicalBookmarks.BOB;

import org.junit.jupiter.api.Test;

import seedu.library.testutil.BookmarkBuilder;

public class BookmarkTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Bookmark bookmark = new BookmarkBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> bookmark.getTags().remove(0));
    }

    @Test
    public void isSameBookmark() {
        // same object -> returns true
        assertTrue(AOT.isSameBookmark(AOT));

        // null -> returns false
        assertFalse(AOT.isSameBookmark(null));

        // same name, all other attributes different -> returns true
        Bookmark editedAlice = new BookmarkBuilder(AOT).withProgress(VALID_PROGRESS_BOB).withGenre(VALID_GENRE_BOB)
                .withAuthor(VALID_AUTHOR_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(AOT.isSameBookmark(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new BookmarkBuilder(AOT).withTitle(VALID_TITLE_BOB).build();
        assertFalse(AOT.isSameBookmark(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Bookmark editedBob = new BookmarkBuilder(BOB).withTitle(VALID_TITLE_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameBookmark(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_TITLE_BOB + " ";
        editedBob = new BookmarkBuilder(BOB).withTitle(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameBookmark(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Bookmark aliceCopy = new BookmarkBuilder(AOT).build();
        assertTrue(AOT.equals(aliceCopy));

        // same object -> returns true
        assertTrue(AOT.equals(AOT));

        // null -> returns false
        assertFalse(AOT.equals(null));

        // different type -> returns false
        assertFalse(AOT.equals(5));

        // different bookmark -> returns false
        assertFalse(AOT.equals(BOB));

        // different name -> returns false
        Bookmark editedAlice = new BookmarkBuilder(AOT).withTitle(VALID_TITLE_BOB).build();
        assertFalse(AOT.equals(editedAlice));

        // different genre -> returns false
        editedAlice = new BookmarkBuilder(AOT).withGenre(VALID_GENRE_BOB).build();
        assertFalse(AOT.equals(editedAlice));
    }

    @Test
    public void validToString() {
        Bookmark bookmark = new BookmarkBuilder(AOT).build();
        assertEquals(AOTString, bookmark.toString());
    }
}
