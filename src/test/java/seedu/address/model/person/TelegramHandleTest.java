package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramHandleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TelegramHandle(null));
    }

    @Test
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        String invalidTelegramHandle = "";
        assertThrows(IllegalArgumentException.class, () -> new TelegramHandle(invalidTelegramHandle));
    }

    @Test
    public void isValidTelegramHandle() {
        // null email
        assertThrows(NullPointerException.class, () -> TelegramHandle.isValidTelegramHandle(null));

        // blank email
        assertFalse(TelegramHandle.isValidTelegramHandle("")); // empty string
        assertFalse(TelegramHandle.isValidTelegramHandle(" ")); // spaces only

        // missing parts
        assertFalse(TelegramHandle.isValidTelegramHandle("linusrichards")); // missing '@' symbol
        assertFalse(TelegramHandle.isValidTelegramHandle("@")); // missing domain name
    }
}
