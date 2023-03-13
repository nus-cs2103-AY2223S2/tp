package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
        //Invalid Tag Names
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
        assertFalse(Tag.isValidTagName("")); // empty string
        assertFalse(Tag.isValidTagName("Tag_That_Has_More_Than_20_Characters")); // more than 30 characters
        assertFalse(Tag.isValidTagName("Tag_That_Has More_Than_20_Characters")); // more than 30 characters
        assertFalse(Tag.isValidTagName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")); //31 characters

        //Valid Tag Names
        assertTrue(Tag.isValidTagName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")); //exactly 30 characters
        assertTrue(Tag.isValidTagName("Game Developer For Apple AAAAA"));
    }

}
