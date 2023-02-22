package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GroupTagTest {

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
    }

    @Test
    public void isValidTagName_validTagValidTarget_true() {
        GroupTag groupTag = new GroupTag("Friend");
        assertTrue(groupTag.isValidTagName("Friend", "Friend"));
    }

    @Test
    public void equals() {
        GroupTag groupTag = new GroupTag("Friend");
        GroupTag otherGroupTag = new GroupTag("Friend");

        assertEquals(groupTag, groupTag);
        assertEquals(groupTag, otherGroupTag);
    }

    @Test
    public void hashCode_validTag_success() {
        String tagName = "Friend";
        GroupTag groupTag = new GroupTag(tagName);
        assertEquals(tagName.hashCode(), groupTag.hashCode());
    }

    @Test
    public void toString_validTag_success() {
        String tagName = "Friend";
        GroupTag groupTag = new GroupTag(tagName);
        assertEquals(String.format("[%s]", tagName), groupTag.toString());
    }

}
