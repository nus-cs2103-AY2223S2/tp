package seedu.connectus.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.connectus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void isValidTagName_null_throwsNullPointerException() {
        // null remark name
        assertThrows(NullPointerException.class, () -> Remark.isValidRemarkName(null));
    }

    @Test
    public void isValidTagName_invalidTags_returnsFalse() {
        // invalid tags
        assertFalse(Tag.isValidTagName("")); // empty string
        assertFalse(Tag.isValidTagName(" ")); // spaces only
    }

    @Test
    public void isValidTagName_validTags_returnsTrue() {
        // valid tags
        assertTrue(Tag.isValidTagName("We Are Friends 123"));
        assertTrue(Tag.isValidTagName("1")); // one character
        assertTrue(Tag.isValidTagName("1 ")); // one character with whitespace
        assertTrue(Tag.isValidTagName("a very very very very very very very very very very long tag")); // long tag
    }

}
