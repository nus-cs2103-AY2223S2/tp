package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Telegram.isValidHandle(null));

        // valid handles - short handles
        assertTrue(Telegram.isValidHandle("@bully")); // lowercase characters only
        assertTrue(Telegram.isValidHandle("@bu11y")); // lowercase with numbers
        assertTrue(Telegram.isValidHandle("@bu_ly")); // lowercase with underscore
        assertTrue(Telegram.isValidHandle("@bu_1y")); // lowercase with numbers and underscore
        assertTrue(Telegram.isValidHandle("@BULLY")); // uppercase characters only
        assertTrue(Telegram.isValidHandle("@Bu11Y")); // uppercase and lowercase with numbers
        assertTrue(Telegram.isValidHandle("@bU_Ly")); // uppercase and lowercase with underscore
        assertTrue(Telegram.isValidHandle("@Bu_1Y")); // uppercase and lowercase with numbers and underscore

        // valid handles - long handles
        assertTrue(Telegram.isValidHandle("@aaaaaaaaaa_bbbbbbbbbb_cccccccccc")); // lowercase with underscore
        // lowercase with underscore and numbers
        assertTrue(Telegram.isValidHandle("@a444444444_bbbbbbbbbb_cccccccccc"));
        assertTrue(Telegram.isValidHandle("@a4444444442H3bh4b23b5HB23b5J2b3h")); // uppercase and lowercase with numbers

        // invalid handles
        assertFalse(Telegram.isValidHandle("@_bull")); // begins with underscore
        assertFalse(Telegram.isValidHandle("@bull_")); // ends with underscore
        assertFalse(Telegram.isValidHandle("@bu__y")); // has consecutive __
        assertFalse(Telegram.isValidHandle("@8ully")); // begins with number
        assertFalse(Telegram.isValidHandle("@bull")); // too short < 5 characters
        assertFalse(Telegram.isValidHandle("@bullybullybullybullybullybullybul")); // too long > 32 characters
        assertFalse(Telegram.isValidHandle("@bu{}y")); // has invalid characters
        assertFalse(Telegram.isValidHandle(" @bully")); // does not start with @
        assertFalse(Telegram.isValidHandle("bully")); // does not start with @
    }
}
