package seedu.address.model.lecture;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LectureNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LectureName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new LectureName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> LectureName.isValidName(null));

        // invalid name
        assertFalse(LectureName.isValidName("")); // empty string
        assertFalse(LectureName.isValidName(" ")); // spaces only
        assertFalse(LectureName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(LectureName.isValidName("LoremIpsum123*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(LectureName.isValidName("lorem ipsum")); // alphabets only
        assertTrue(LectureName.isValidName("42")); // numbers only
        assertTrue(LectureName.isValidName("lorem ipsum 01")); // alphanumeric characters
        assertTrue(LectureName.isValidName("Lorem Ipsum")); // with capital letters
    }

}
