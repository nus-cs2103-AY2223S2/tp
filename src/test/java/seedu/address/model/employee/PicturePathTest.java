package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PicturePathTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PicturePath(null));
    }

    @Test
    public void constructor_invalidPicturePath_throwsIllegalArgumentException() {
        String invalidPicturePath = "";
        assertThrows(IllegalArgumentException.class, () -> new PicturePath(invalidPicturePath));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> PicturePath.isValidPicturePath(null));

        // invalid addresses
        assertFalse(PicturePath.isValidPicturePath("")); // empty string
        assertFalse(PicturePath.isValidPicturePath("src/main/default.png")); // invalid directory
        // invalid file type
        assertFalse(PicturePath.isValidPicturePath("src/main/resources/employeepictures/default.jpg"));


        // valid addresses
        assertTrue(PicturePath.isValidPicturePath("src/main/resources/employeepictures/default.png"));
        // inside another directory
        assertTrue(PicturePath.isValidPicturePath("src/main/resources/employeepictures/anotherfolder/default.png"));
        // file does not exist, but still valid
        assertTrue(PicturePath.isValidPicturePath("src/main/resources/employeepictures/doesnotexist.png"));
    }
}
