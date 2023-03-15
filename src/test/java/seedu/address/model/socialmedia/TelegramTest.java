package seedu.address.model.socialmedia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TelegramTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Telegram.of(null));
    }

    @Test
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Telegram.of("inva...liddooo123"));
    }

    @Test
    public void constructor_working_example() {
        var username = "va_lid_username";
        assertTrue(Telegram.isValid(username));
        assertEquals(Telegram.of(username).toString(), username);
    }
}
