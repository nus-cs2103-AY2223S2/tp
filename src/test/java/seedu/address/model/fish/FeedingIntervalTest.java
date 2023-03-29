package seedu.address.model.fish;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FeedingIntervalTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FeedingInterval(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new FeedingInterval(invalidAddress));
    }

    @Test
    public void isValidFeedingInterval() {
        // null address
        assertThrows(NullPointerException.class, () -> FeedingInterval.isValidFeedingInterval(null));

        // invalid addresses
        assertFalse(FeedingInterval.isValidFeedingInterval("")); // empty string
        assertFalse(FeedingInterval.isValidFeedingInterval(" ")); // spaces only
        assertFalse(FeedingInterval.isValidFeedingInterval("1dnh")); // wrong format
        assertFalse(FeedingInterval.isValidFeedingInterval("1h5h")); // wrong format

        // valid addresses
        assertTrue(FeedingInterval.isValidFeedingInterval("1d5h"));
        assertTrue(FeedingInterval.isValidFeedingInterval("15d0h"));
        assertTrue(FeedingInterval.isValidFeedingInterval("0d1h"));
    }
}
