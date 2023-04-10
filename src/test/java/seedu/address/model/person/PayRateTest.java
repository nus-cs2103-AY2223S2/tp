package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PayRateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PayRate(null));
    }

    @Test
    public void constructor_invalidPayRate_throwsIllegalArgumentException() {
        String invalidPayRate = "";
        assertThrows(IllegalArgumentException.class, () -> new PayRate(invalidPayRate));
    }

    @Test
    public void isValidPayRate() {
        // null pay rate
        assertThrows(NullPointerException.class, () -> PayRate.isValidPayRate(null));

        // blank pay rate
        assertFalse(PayRate.isValidPayRate("")); // empty string
        assertFalse(PayRate.isValidPayRate(" ")); // spaces only

        // invalid parts
        assertFalse(PayRate.isValidPayRate("-1")); // negative pay
        assertFalse(PayRate.isValidPayRate("4.5")); //  decimal pay

        // valid email
        assertTrue(PayRate.isValidPayRate("0")); // underscore in local part
        assertTrue(PayRate.isValidPayRate("45")); // period in local part
    }
}
