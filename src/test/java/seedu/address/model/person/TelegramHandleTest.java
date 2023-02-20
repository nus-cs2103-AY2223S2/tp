package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

        // invalid argument
        assertFalse(TelegramHandle.isValidTelegramHandle(
                "@linusrichards ")); // padded with space at the back
        assertFalse(TelegramHandle.isValidTelegramHandle(
                " @linusrichards")); // padded with space at the front
        assertFalse(TelegramHandle.isValidTelegramHandle(
                "@linus richards")); // space found in the middle
        assertFalse(TelegramHandle.isValidTelegramHandle(
                "@linus @richards")); // parse two telegram handles
        assertFalse(TelegramHandle.isValidTelegramHandle(
                "@linus@richards")); // parse two telegram handles without space
        assertFalse(TelegramHandle.isValidTelegramHandle(
                "@@linusrichards")); // double @ symbol
        assertFalse(TelegramHandle.isValidTelegramHandle(
                "@linus!richards ")); // non alnum character found

        // valid argument
        assertTrue(TelegramHandle.isValidTelegramHandle(
                "@linusrichards")); // standard lower case
        assertTrue(TelegramHandle.isValidTelegramHandle(
                "@LinusRichards")); // with upper case
        assertTrue(TelegramHandle.isValidTelegramHandle(
                "@linus2richards3")); // with numeric
        assertTrue(TelegramHandle.isValidTelegramHandle(
                "@Linus2Richards3")); // with upper case and numeric
    }
}
