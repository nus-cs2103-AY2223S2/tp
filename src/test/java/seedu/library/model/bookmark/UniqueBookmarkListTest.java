package seedu.library.model.bookmark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.logic.commands.CommandTestUtil.VALID_AUTHOR_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.library.testutil.Assert.assertThrows;
import static seedu.library.testutil.TypicalBookmarks.AOT;
import static seedu.library.testutil.TypicalBookmarks.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.library.model.bookmark.exceptions.BookmarkNotFoundException;
import seedu.library.model.bookmark.exceptions.DuplicateBookmarkException;
import seedu.library.testutil.BookmarkBuilder;

public class UniqueBookmarkListTest {

    private final UniqueBookmarkList uniqueBookmarkList = new UniqueBookmarkList();

    @Test
    public void contains_nullBookmark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookmarkList.contains(null));
    }

    @Test
    public void contains_bookmarkNotInList_returnsFalse() {
        assertFalse(uniqueBookmarkList.contains(AOT));
    }

    @Test
    public void contains_bookmarkInList_returnsTrue() {
        uniqueBookmarkList.add(AOT);
        assertTrue(uniqueBookmarkList.contains(AOT));
    }

    @Test
    public void contains_bookmarkWithSameIdentityFieldsInList_returnsTrue() {
        uniqueBookmarkList.add(AOT);
        Bookmark editedAlice = new BookmarkBuilder(AOT).withAuthor(VALID_AUTHOR_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueBookmarkList.contains(editedAlice));
    }

    @Test
    public void add_nullBookmark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookmarkList.add(null));
    }

    @Test
    public void add_duplicateBookmark_throwsDuplicateBookmarkException() {
        uniqueBookmarkList.add(AOT);
        assertThrows(DuplicateBookmarkException.class, () -> uniqueBookmarkList.add(AOT));
    }

    @Test
    public void setBookmark_nullTargetBookmark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookmarkList.setBookmark(null, AOT));
    }

    @Test
    public void setBookmark_nullEditedBookmark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookmarkList.setBookmark(AOT, null));
    }

    @Test
    public void setBookmark_targetBookmarkNotInList_throwsBookmarkNotFoundException() {
        assertThrows(BookmarkNotFoundException.class, () -> uniqueBookmarkList.setBookmark(AOT, AOT));
    }

    @Test
    public void setBookmark_editedBookmarkIsSameBookmark_success() {
        uniqueBookmarkList.add(AOT);
        uniqueBookmarkList.setBookmark(AOT, AOT);
        UniqueBookmarkList expectedUniqueBookmarkList = new UniqueBookmarkList();
        expectedUniqueBookmarkList.add(AOT);
        assertEquals(expectedUniqueBookmarkList, uniqueBookmarkList);
    }

    @Test
    public void setBookmark_editedBookmarkHasSameIdentity_success() {
        uniqueBookmarkList.add(AOT);
        Bookmark editedAlice = new BookmarkBuilder(AOT).withAuthor(VALID_AUTHOR_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueBookmarkList.setBookmark(AOT, editedAlice);
        UniqueBookmarkList expectedUniqueBookmarkList = new UniqueBookmarkList();
        expectedUniqueBookmarkList.add(editedAlice);
        assertEquals(expectedUniqueBookmarkList, uniqueBookmarkList);
    }

    @Test
    public void setBookmark_editedBookmarkHasDifferentIdentity_success() {
        uniqueBookmarkList.add(AOT);
        uniqueBookmarkList.setBookmark(AOT, BOB);
        UniqueBookmarkList expectedUniqueBookmarkList = new UniqueBookmarkList();
        expectedUniqueBookmarkList.add(BOB);
        assertEquals(expectedUniqueBookmarkList, uniqueBookmarkList);
    }

    @Test
    public void setBookmark_editedBookmarkHasNonUniqueIdentity_throwsDuplicateBookmarkException() {
        uniqueBookmarkList.add(AOT);
        uniqueBookmarkList.add(BOB);
        assertThrows(DuplicateBookmarkException.class, () -> uniqueBookmarkList.setBookmark(AOT, BOB));
    }

    @Test
    public void remove_nullBookmark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookmarkList.remove(null));
    }

    @Test
    public void remove_bookmarkDoesNotExist_throwsBookmarkNotFoundException() {
        assertThrows(BookmarkNotFoundException.class, () -> uniqueBookmarkList.remove(AOT));
    }

    @Test
    public void remove_existingBookmark_removesBookmark() {
        uniqueBookmarkList.add(AOT);
        uniqueBookmarkList.remove(AOT);
        UniqueBookmarkList expectedUniqueBookmarkList = new UniqueBookmarkList();
        assertEquals(expectedUniqueBookmarkList, uniqueBookmarkList);
    }

    @Test
    public void setBookmarks_nullUniqueBookmarkList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookmarkList.setBookmarks((UniqueBookmarkList) null));
    }

    @Test
    public void setBookmarks_uniqueBookmarkList_replacesOwnListWithProvidedUniqueBookmarkList() {
        uniqueBookmarkList.add(AOT);
        UniqueBookmarkList expectedUniqueBookmarkList = new UniqueBookmarkList();
        expectedUniqueBookmarkList.add(BOB);
        uniqueBookmarkList.setBookmarks(expectedUniqueBookmarkList);
        assertEquals(expectedUniqueBookmarkList, uniqueBookmarkList);
    }

    @Test
    public void setBookmarks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookmarkList.setBookmarks((List<Bookmark>) null));
    }

    @Test
    public void setBookmarks_list_replacesOwnListWithProvidedList() {
        uniqueBookmarkList.add(AOT);
        List<Bookmark> bookmarkList = Collections.singletonList(BOB);
        uniqueBookmarkList.setBookmarks(bookmarkList);
        UniqueBookmarkList expectedUniqueBookmarkList = new UniqueBookmarkList();
        expectedUniqueBookmarkList.add(BOB);
        assertEquals(expectedUniqueBookmarkList, uniqueBookmarkList);
    }

    @Test
    public void setBookmarks_listWithDuplicateBookmarks_throwsDuplicateBookmarkException() {
        List<Bookmark> listWithDuplicateBookmarks = Arrays.asList(AOT, AOT);
        assertThrows(DuplicateBookmarkException.class, () -> uniqueBookmarkList
                .setBookmarks(listWithDuplicateBookmarks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueBookmarkList.asUnmodifiableObservableList().remove(0));
    }
}
