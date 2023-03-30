package seedu.connectus.model.socialmedia;

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
        assertThrows(IllegalArgumentException.class, () -> Instagram.of("inva...liddooo123")); // consecutive .
        assertThrows(IllegalArgumentException.class, () -> Instagram.of("invaliddooo123.")); // end with .
        assertThrows(IllegalArgumentException.class, () -> Instagram.of(".invaliddooo123")); // start with .
        assertThrows(IllegalArgumentException.class, () ->
                Instagram.of("invalid.invalid.invalid.123456.")); // > 30 characters
    }

    @Test
    public void constructor_validInstagram_parseSuccess() {
        assertTrue(Instagram.isValid("validUsername"));
        assertTrue(Instagram.isValid("a.valid.Username.123"));
        assertTrue(Instagram.isValid("32123"));
        assertTrue(Instagram.isValid("a"));
        assertTrue(Instagram.isValid("jason.jason.jason.jason.jason")); // characters
    }

    @Test
    public void constructor_working_example() {
        var username = "va.li.d1.username";
        assertTrue(Instagram.isValid(username));
        assertEquals(Instagram.of(username).toString(), username);
    }
}
