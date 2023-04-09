package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

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
    public void isValidPicturePath() {
        // null address
        assertThrows(NullPointerException.class, () -> PicturePath.isValidPicturePath(null));

        // invalid addresses
        assertFalse(PicturePath.isValidPicturePath("")); // empty string
        assertFalse(PicturePath.isValidPicturePath("src/main/default_employee.png")); // invalid directory
        // invalid file type
        assertFalse(PicturePath.isValidPicturePath("src/main/resources/employeepictures/default.jpg"));


        // valid addresses
        assertTrue(PicturePath.isValidPicturePath("src/main/resources/employeepictures/default_employee.png"));
        // inside another directory
        assertTrue(PicturePath.isValidPicturePath("src/main/resources/employeepictures/anotherfolder/default_employee.png"));
        // file does not exist, but still valid
        assertTrue(PicturePath.isValidPicturePath("src/main/resources/employeepictures/doesnotexist.png"));
    }

    @Test
    public void toPath() {
        PicturePath picturePath = new PicturePath("src/main/resources/employeepictures/default_employee.png");
        Path expectedPath = Paths.get("src", "main", "resources", "employeepictures", "default_employee.png");
        assertTrue(expectedPath.equals(picturePath.toPath()));
    }

    @Test
    public void equals() {
        // same values -> returns true
        PicturePath defaultPicturePath = new PicturePath("src/main/resources/employeepictures/default_employee.png");
        PicturePath defaultPicturePathCopy = new PicturePath("src/main/resources/employeepictures/default_employee.png");
        assertTrue(defaultPicturePath.equals(defaultPicturePathCopy));

        // same object -> returns true
        assertTrue(defaultPicturePath.equals(defaultPicturePath));

        // null -> returns false
        assertFalse(defaultPicturePath.equals(null));

        // different type -> returns false
        assertFalse(defaultPicturePath.equals("src/main/resources/employeepictures/default_employee.png"));

        // different employee -> returns false
        PicturePath anotherPicturePath = new PicturePath("src/main/resources/employeepictures/another.png");
        assertFalse(defaultPicturePath.equals(anotherPicturePath));
    }
}
