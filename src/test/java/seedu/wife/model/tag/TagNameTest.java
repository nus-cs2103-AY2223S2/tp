package seedu.wife.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.wife.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.wife.logic.commands.CommandTestUtil;

public class TagNameTest {
    private static final String name = "test";
    private final TagName tagName = new TagName(name);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagName(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TagName(
                CommandTestUtil.INVALID_TAG_NAME_EMPTY_NAME
        ));
        assertThrows(IllegalArgumentException.class, () -> new TagName(
                CommandTestUtil.INVALID_TAG_NAME_CONTAIN_SYMBOL
        ));
        assertThrows(IllegalArgumentException.class, () -> new TagName(
                CommandTestUtil.INVALID_TAG_NAME_TOO_LENGTHY
        ));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> TagName.isValidTagName(null));
    }

    @Test
    public void tag_isEqual_anotherTag() {
        final TagName tagNameCopy = new TagName(name);
        assertEquals(tagNameCopy, tagName);
    }

    @Test
    public void tag_isNotEqual_anotherTag() {
        final TagName tagNameCopy = new TagName("anotherTag");
        assertNotEquals(tagNameCopy, tagName);
    }
}
