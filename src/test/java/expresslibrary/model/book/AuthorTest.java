package expresslibrary.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AuthorTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Author(null));
    }

    @Test
    public void constructor_invalidAuthor_throwsIllegalArgumentException() {
        String invalidAuthor = "";
        assertThrows(IllegalArgumentException.class, () -> new Author(invalidAuthor));
    }

    @Test
    public void isValidAuthor() {
        // null author
        assertThrows(NullPointerException.class, () -> Author.isValidAuthor(null));

        // invalid authors
        assertFalse(Author.isValidAuthor("")); // empty string
        assertFalse(Author.isValidAuthor(" ")); // spaces only
        assertFalse(Author.isValidAuthor("J.K Rowling")); // special characters

        // valid authors
        assertTrue(Author.isValidAuthor("JK Rowling"));
        assertTrue(Author.isValidAuthor("A")); // one character
        assertTrue(Author.isValidAuthor("Emma Dorothy Eliza Nevitte Southworth")); // long name
    }
}
