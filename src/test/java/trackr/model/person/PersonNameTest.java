package trackr.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PersonNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new PersonName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> PersonName.isValidName(null));

        // invalid name
        assertFalse(PersonName.isValidName("")); // empty string
        assertFalse(PersonName.isValidName(" ")); // spaces only
        assertFalse(PersonName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(PersonName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(PersonName.isValidName("peter jack")); // alphabets only
        assertTrue(PersonName.isValidName("12345")); // numbers only
        assertTrue(PersonName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(PersonName.isValidName("Capital Tan")); // with capital letters
        assertTrue(PersonName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void equals() {
        PersonName name = new PersonName("Capital Tan");
        PersonName differentName = new PersonName("12345");

        assertTrue(name.equals(name)); //same object
        assertTrue(name.equals(new PersonName("Capital Tan"))); //same name

        assertFalse(name.equals(null)); //null
        assertFalse(name.equals(differentName)); //different name
        assertFalse(name.equals(1)); //different type
    }
}
