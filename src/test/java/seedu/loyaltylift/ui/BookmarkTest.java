package seedu.loyaltylift.ui;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BookmarkTest {

    @Test
    public void createBookmark_nullMarked_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Bookmark.createBookmarkIcon(null));
    }
}
