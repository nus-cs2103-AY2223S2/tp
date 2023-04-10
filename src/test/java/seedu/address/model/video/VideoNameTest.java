package seedu.address.model.video;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class VideoNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new VideoName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new VideoName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> VideoName.isValidName(null));

        // invalid name
        assertFalse(VideoName.isValidName("")); // empty string
        assertFalse(VideoName.isValidName(" ")); // spaces only
        assertFalse(VideoName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(VideoName.isValidName("Lorem Ipsum 123*")); // contains non-alphanumeric or space characters

        // valid name
        assertTrue(VideoName.isValidName("lorem ipsum")); // alphabets only
        assertTrue(VideoName.isValidName("42")); // numbers only
        assertTrue(VideoName.isValidName("lorem ipsum 01")); // alphanumeric characters
        assertTrue(VideoName.isValidName("Lorem Ipsum")); // with capital letters
    }

    @Test
    public void compareTo() {
        VideoName name = new VideoName("CbA");
        VideoName otherName = new VideoName("AbC");

        // same case
        assertTrue(name.compareTo(name) == 0);
        assertTrue(name.compareTo(otherName) > 0);
        assertTrue(otherName.compareTo(name) < 0);

        // different case, same order
        name = new VideoName("abc");
        otherName = new VideoName("ABC");

        assertTrue(name.compareTo(otherName) == 0);

        // different case, upper case has higher order
        name = new VideoName("CDE");
        otherName = new VideoName("abc");

        assertTrue(name.compareTo(otherName) > 0);
        assertTrue(otherName.compareTo(name) < 0);

        // different case, lower case has higher order
        name = new VideoName("cde");
        otherName = new VideoName("ABC");

        assertTrue(name.compareTo(otherName) > 0);
        assertTrue(otherName.compareTo(name) < 0);
    }

}
