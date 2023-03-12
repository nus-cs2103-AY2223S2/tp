package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidBirthday_throwsIllegalArgumentException() {
        String invalidBirthday = "";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthday));

        String invalidBirthdayTonight = "tonight";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthdayTonight));

    }

    @Test
    public void constructor_working_example() {
        String validBirthday = "01/01/2000";
        assertTrue(Birthday.isValidBirthday(validBirthday));

        Birthday bd = new Birthday(validBirthday);
        assertTrue(bd.toString().equals("Jan 01 2000"));
    }

}
