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
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // valid tag names
        assertTrue(Tag.isValidTagName("happyKitty"));
        assertTrue(Tag.isValidTagName("hello123"));
        assertTrue(Tag.isValidTagName("smilly12HAPPY"));

        // invalid tag names
        assertFalse(Tag.isValidTagName("happy_kitty"));
        assertFalse(Tag.isValidTagName("letter with space"));
        assertFalse(Tag.isValidTagName("specialCharacters@"));
        assertFalse(Tag.isValidTagName("special%#!"));
        assertFalse(Tag.isValidTagName("characters/"));
        assertFalse(Tag.isValidTagName("[happy]"));
    }

}
