package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RatingTest {
    private static final Rating RATING_A = new Rating("3.5");
    private static final Rating RATING_B = new Rating("3.5");
    private static final Rating RATING_C = new Rating("4.0");

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(RATING_A.equals(RATING_A));

        // same value -> returns true
        assertTrue(RATING_A.equals(RATING_B));

        // different types -> returns false
        assertFalse(RATING_A.equals("3.5"));

        // null -> returns false
        assertFalse(RATING_A.equals(null));

        // different value -> returns false
        assertFalse(RATING_A.equals(RATING_C));
    }

    @Test
    void testHashCode() {
        // same value -> returns same hashcode
        assertEquals(RATING_A.hashCode(), RATING_B.hashCode());

        // different value -> returns different hashcode
        assertNotEquals(RATING_A.hashCode(), RATING_C.hashCode());
    }
}

