package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class PhotoTest {

    @Test
    public void equals() {
        Photo photo = new Photo("/images/studentProfiles/student_1.png");

        // same object -> returns true
        assertTrue(photo.equals(photo));

        // same values -> returns true
        Photo photoCopy = new Photo(photo.photoFilePath);
        assertTrue(photo.equals(photoCopy));

        // different types -> returns false
        assertFalse(photo.equals(1));

        // null -> returns false
        assertFalse(photo.equals(null));

        // different photo -> returns false
        Photo differentPhoto = new Photo("/images/studentProfiles/student_2.png");
        assertFalse(photo.equals(differentPhoto));
    }

    @Test
    public void default_setDefaultDimensions_success() {
        GuiSettings guiSettings = new GuiSettings();
        Photo photo = new Photo("/images/studentProfiles/student_2.png");
        photo.setDefaultDimensions(guiSettings);
    }

    @Test
    public void getHeight_getterMethod_success() {
        Photo photo = new Photo("/images/studentProfiles/student_2.png");
        assertEquals(35, photo.getHeight());
    }

    @Test
    public void getWidth_getterMethod_success() {
        Photo photo = new Photo("/images/studentProfiles/student_2.png");
        assertEquals(40, photo.getWidth());
    }

    @Test
    public void getCoordinateX_getCircle_success() {
        Photo photo = new Photo("/images/studentProfiles/student_2.png");
        assertEquals(17, photo.getCircleX());
    }

    @Test
    public void getCoordinateY_getCircle_success() {
        Photo photo = new Photo("/images/studentProfiles/student_2.png");
        assertEquals(17, photo.getCircleY());
    }

    @Test
    public void getRadius_getCircle_success() {
        Photo photo = new Photo("/images/studentProfiles/student_2.png");
        assertEquals(17, photo.getCircleRadius());
    }

    @Test
    public void getHash_hasCode_success() {
        Photo photo = new Photo("/images/studentProfiles/student_2.png");
        photo.hashCode();
    }
}
