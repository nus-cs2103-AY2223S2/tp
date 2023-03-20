package expresslibrary.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsbnTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Isbn(null));
    }

    @Test
    public void constructor_invalidIsbn_throwsIllegalArgumentException() {
        String invalidIsbn = "";
        assertThrows(IllegalArgumentException.class, () -> new Isbn(invalidIsbn));
    }

    @Test
    public void isValidIsbn() {
        // null ISBN
        assertThrows(NullPointerException.class, () -> Isbn.isValidIsbn(null));

        // invalid ISBNs
        assertFalse(Isbn.isValidIsbn("")); // empty string
        assertFalse(Isbn.isValidIsbn(" ")); // spaces only
        assertFalse(Isbn.isValidIsbn("12341234")); // too little numbers

        // valid ISBNs
        assertTrue(Isbn.isValidIsbn("1239082130")); // 10 digits
        assertTrue(Isbn.isValidIsbn("1239123012301")); // 13 digits
        assertTrue(Isbn.isValidIsbn("0000000000"));
        assertTrue(Isbn.isValidIsbn("0000000000000"));
    }
}
