package trackr.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

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
    public void equals() {
        Tag tag = new Tag("first");
        Tag differentTag = new Tag("second");

        assertTrue(tag.equals(tag)); //same object
        assertTrue(tag.equals(new Tag("first"))); //same task name

        assertFalse(tag.equals(null)); //null
        assertFalse(tag.equals(differentTag)); //different task name
        assertFalse(tag.equals(1)); //different type
    }
}
