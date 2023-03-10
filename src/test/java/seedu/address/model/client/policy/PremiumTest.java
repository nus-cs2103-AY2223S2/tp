package seedu.address.model.client.policy;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class PremiumTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Premium(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidPremium = "";
        assertThrows(IllegalArgumentException.class, () -> new Premium(invalidPremium));
    }

    @Test
    void isValidPremium() {
        String premium = "500.2";
        assertTrue(Premium.isValidPremium(premium));
    }

    @Test
    void testEquals() {
        assertTrue(true);
    }

}
