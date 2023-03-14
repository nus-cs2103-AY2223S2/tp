package seedu.library.model.bookmark;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenreTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Genre(null));
    }

    @Test
    public void constructor_invalidGenre_throwsIllegalArgumentException() {
        String invalidGenre = "";
        assertThrows(IllegalArgumentException.class, () -> new Genre(invalidGenre));
    }

    @Test
    public void isValidGenre() {
        // null genre
        assertThrows(NullPointerException.class, () -> Genre.isValidGenre(null));

        // blank genre
        assertFalse(Genre.isValidGenre("")); // empty string
        assertFalse(Genre.isValidGenre(" ")); // spaces only

        // valid genre
        assertTrue(Genre.isValidGenre("Action"));
        assertTrue(Genre.isValidGenre("-")); // one character
    }
}
