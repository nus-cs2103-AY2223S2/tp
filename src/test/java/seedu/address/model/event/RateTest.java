package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rate(null));
    }

    @Test
    public void constructor_invalidRate_throwsIllegalArgumentException() {
        String invalidRate = "";
        assertThrows(IllegalArgumentException.class, () -> new Rate(invalidRate));
    }

    @Test
    public void isValidRate() {
        // null rate number
        assertThrows(NullPointerException.class, () -> Rate.isValidRate(null));

        // invalid rate numbers
        assertFalse(Rate.isValidRate("")); // empty string
        assertFalse(Rate.isValidRate(" ")); // spaces only
        assertFalse(Rate.isValidRate("Rate")); // non-numeric
        assertFalse(Rate.isValidRate("9011p041")); // alphabets within digits
        assertFalse(Rate.isValidRate("9312 1534")); // spaces within digits

        // valid rate numbers
        assertTrue(Rate.isValidRate("911.00"));
        assertTrue(Rate.isValidRate("93121534"));
        assertTrue(Rate.isValidRate("124293842033123")); // long rate numbers
    }
}
