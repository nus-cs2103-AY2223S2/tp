package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ReviewTest {
    @Test
    public void equals() {
        Review review1 = new Review("This internship was amazing!");
        Review review2 = new Review("This internship was amazing!");
        Review review3 = new Review("This internship was terrible!");

        // reflexivity
        assertTrue(review1.equals(review1));

        // symmetry
        assertTrue(review1.equals(review2));
        assertTrue(review2.equals(review1));

        // transitivity
        assertTrue(review1.equals(review2));
        assertFalse(review2.equals(review3));
        assertFalse(review1.equals(review3));

        // non-nullity
        assertFalse(review1.equals(null));

        // unequal objects
        assertFalse(review1.equals(new Object()));
    }

}
