package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GroupTagTest {

    private static final String GROUP_TAG_STRING = "Friend";
    private static final GroupTag GROUP_TAG = new GroupTag(GROUP_TAG_STRING);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupTag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new GroupTag(invalidTagName));
    }

    @Test
    public void isValidTagName_null_throwsNullPointerException() {
        // null tag name
        assertThrows(NullPointerException.class, () -> GroupTag.isValidTagName(null));
    }

    @Test
    public void isValidTagName_validTagName_true() {
        assertTrue(GroupTag.isValidTagName(
                "friend")); // standard format
        assertTrue(GroupTag.isValidTagName(
                "Friend")); // capitalized format
        assertTrue(GroupTag.isValidTagName(
                "FRIEND")); // all caps format
        assertTrue(GroupTag.isValidTagName(
                "Fr1end")); // contains numeric
        assertTrue(GroupTag.isValidTagName(
                "12345")); // full numeric
    }

    @Test
    public void isValidTagName_invalidTagName_false() {
        assertFalse(GroupTag.isValidTagName(
                "NUS Friend")); // multiple tags at once
        assertFalse(GroupTag.isValidTagName(
                "")); // empty string
        assertFalse(GroupTag.isValidTagName(
                "!Friend")); // contains non-alphanumeric
        assertFalse(GroupTag.isValidTagName(
                "FriendWithAVeryLongTagNameThatIsOver20Characters"));
    }

    @Test
    public void isValidTagName_validTagValidTarget_true() {
        assertTrue(GROUP_TAG.isValidTagName("Friend", "Friend"));
    }

    @Test
    public void equals() {
        GroupTag otherGroupTag = new GroupTag("Friend");

        assertEquals(GROUP_TAG, GROUP_TAG);
        assertEquals(GROUP_TAG, otherGroupTag);
    }

    @Test
    public void hashCode_validTag_success() {
        assertEquals(GROUP_TAG_STRING.hashCode(), GROUP_TAG.hashCode());
    }

    @Test
    public void toString_validTag_success() {
        assertEquals(String.format("%s", GROUP_TAG_STRING), GROUP_TAG.toString());
    }

}
