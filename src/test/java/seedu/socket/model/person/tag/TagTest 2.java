package seedu.socket.model.person.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // invalid tag name
        assertFalse(Tag.isValidTagName("-")); // special chars
        assertFalse(Tag.isValidTagName(" ")); // space
        assertFalse(Tag.isValidTagName(" tag")); // leading space
        assertFalse(Tag.isValidTagName("tag ")); // trailing space
        assertFalse(Tag.isValidTagName("invalid tag")); // with space
        assertFalse(Tag.isValidTagName("012345678901234567890")); // exceed 20 chars

        // valid tag name
        assertTrue(Tag.isValidTagName("tag")); // alphabet
        assertTrue(Tag.isValidTagName("012")); // numeric
        assertTrue(Tag.isValidTagName("tag1")); // alphanumeric
        assertTrue(Tag.isValidTagName("01234567890123456789")); // 20 chars
    }

}
