package seedu.library.model.bookmark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RatingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_invalidRating_throwsIllegalArgumentException() {
        String invalidRating = "";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating));
    }

    @Test
    public void isValidRating() {
        // null rating
        assertThrows(NullPointerException.class, () -> Rating.isValidRating(null));

        // invalid rating
        assertFalse(Rating.isValidRating("")); // empty string
        assertFalse(Rating.isValidRating("-1")); // negative value
        assertFalse(Rating.isValidRating("6")); // value above 5

        // valid rating
        assertTrue(Rating.isValidRating("0")); // lowest value possible
        assertTrue(Rating.isValidRating("5")); // highest value possible
        assertTrue(Rating.isValidRating("3")); // value in valid range
    }

    @Test
    public void validStringRepresentation() {
        Rating rating = new Rating("4");
        assertEquals("4", rating.toString());
    }
}
