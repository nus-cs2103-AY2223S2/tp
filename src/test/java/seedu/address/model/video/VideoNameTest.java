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
        assertFalse(VideoName.isValidName("LoremIpsum123*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(VideoName.isValidName("lorem ipsum")); // alphabets only
        assertTrue(VideoName.isValidName("42")); // numbers only
        assertTrue(VideoName.isValidName("lorem ipsum 01")); // alphanumeric characters
        assertTrue(VideoName.isValidName("Lorem Ipsum")); // with capital letters
    }

}
