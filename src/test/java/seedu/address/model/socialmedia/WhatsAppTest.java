package seedu.address.model.socialmedia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WhatsAppTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> WhatsApp.of(null));
    }

    @Test
    public void constructor_invalidWhatsApp_throwsIllegalArgumentException() {
        String invalidWhatsApp = " ";
        assertThrows(IllegalArgumentException.class, () -> WhatsApp.of(invalidWhatsApp));
    }

    @Test
    public void isValidWhatsApp() {
        // null phone number
        assertThrows(NullPointerException.class, () -> WhatsApp.isValidWhatsApp(null));

        // invalid phone numbers
        assertFalse(WhatsApp.isValidWhatsApp("")); // empty string
        assertFalse(WhatsApp.isValidWhatsApp(" ")); // spaces only
        assertFalse(WhatsApp.isValidWhatsApp("91")); // less than 3 numbers
        assertFalse(WhatsApp.isValidWhatsApp("phone")); // non-numeric
        assertFalse(WhatsApp.isValidWhatsApp("9011p041")); // alphabets within digits
        assertFalse(WhatsApp.isValidWhatsApp("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(WhatsApp.isValidWhatsApp("911")); // exactly 3 numbers
        assertTrue(WhatsApp.isValidWhatsApp("93121534"));
        assertTrue(WhatsApp.isValidWhatsApp("124293842033123")); // long phone numbers
    }
}
