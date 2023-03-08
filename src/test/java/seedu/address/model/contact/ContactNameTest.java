package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ContactNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ContactName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ContactName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> ContactName.isValidName(null));

        // invalid name
        assertFalse(ContactName.isValidName("")); // empty string
        assertFalse(ContactName.isValidName(" ")); // spaces only
        assertFalse(ContactName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(ContactName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(ContactName.isValidName("peter jack")); // alphabets only
        assertTrue(ContactName.isValidName("12345")); // numbers only
        assertTrue(ContactName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(ContactName.isValidName("Capital Tan")); // with capital letters
        assertTrue(ContactName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

}
