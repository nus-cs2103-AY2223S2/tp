package seedu.careflow.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.careflow.testutil.StringUtil;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("$peter*")); // contains special characters that are not allowed in name
        assertFalse(Name.isValidName(StringUtil.generateRandomString(51))); // long name exceed 50 char limit

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("KK Women's and Children's Hospital")); // with capital letters
        assertTrue(Name.isValidName("peter's Ang.")); // contains special characters that are allowed in name
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName("a" + StringUtil.generateRandomString(49))); // long names with 50 char
    }

    @Test
    public void equals() {
        Name name = new Name("Alice Teoh");
        // null -> returns false
        assertFalse(name.equals(null));

        // same object -> returns true
        assertTrue(name.equals(name));

        // same values -> returns true
        assertTrue(name.equals(new Name("Alice Teoh")));

        // different values -> return false
        assertFalse(name.equals("Alice Tan"));
    }
}
