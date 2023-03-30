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
        assertFalse(LectureName.isValidName("Lorem Ipsum 123*")); // contains non-alphanumeric or space characters

        // valid name
        assertTrue(LectureName.isValidName("lorem ipsum")); // alphabets only
        assertTrue(LectureName.isValidName("42")); // numbers only
        assertTrue(LectureName.isValidName("lorem ipsum 01")); // alphanumeric characters
        assertTrue(LectureName.isValidName("Lorem Ipsum")); // with capital letters
    }

    @Test
    public void compareTo() {
        LectureName name = new LectureName("CbA");
        LectureName otherName = new LectureName("AbC");

        // same case
        assertTrue(name.compareTo(name) == 0);
        assertTrue(name.compareTo(otherName) > 0);
        assertTrue(otherName.compareTo(name) < 0);

        // different case, same order
        name = new LectureName("abc");
        otherName = new LectureName("ABC");

        assertTrue(name.compareTo(otherName) == 0);

        // different case, upper case has higher order
        name = new LectureName("CDE");
        otherName = new LectureName("abc");

        assertTrue(name.compareTo(otherName) > 0);
        assertTrue(otherName.compareTo(name) < 0);

        // different case, lower case has higher order
        name = new LectureName("cde");
        otherName = new LectureName("ABC");

        assertTrue(name.compareTo(otherName) > 0);
        assertTrue(otherName.compareTo(name) < 0);
    }


}
