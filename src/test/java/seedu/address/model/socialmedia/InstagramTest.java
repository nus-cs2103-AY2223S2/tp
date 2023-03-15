package seedu.address.model.socialmedia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InstagramTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Instagram.of(null));
    }

    @Test
    public void constructor_invalidInstagram_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Instagram.of("inva...liddooo123"));
    }

    @Test
    public void constructor_working_example() {
        var username = "va.li.d1.username";
        assertTrue(Instagram.isValid(username));
        assertEquals(Instagram.of(username).toString(), username);
    }
}
