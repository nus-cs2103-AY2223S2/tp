package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Locale;

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
    }

    @Test
    public void isValidLengthTagName() {
        // null tag name
        String tooLongTagName = "oooooooooooooooooooooooooooooooooooooooo";
        String acceptableTagName = "hello";
        assertEquals(false, Tag.isValidLengthTagName(tooLongTagName));
        assertEquals(true, Tag.isValidLengthTagName(acceptableTagName));
    }

    @Test
    public void getTagTest() {
        String tagName = "Hi";
        Tag tag = new Tag(tagName);
        assertEquals(tagName.toLowerCase(Locale.ROOT), tag.getTag());
    }

    @Test
    public void isSameTagTest() {
        String tagName = "Hi";
        Tag tag1 = new Tag(tagName);
        Tag tag2 = new Tag(tagName);
        assertEquals(tag1, tag2);
    }
}
