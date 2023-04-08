package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateOfBirthTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void isValidDateOfBirth() {
        // null dates
        assertThrows(NullPointerException.class, () -> DateOfBirth.isValidDate(null));

        // empty string for dates
        assertFalse(DateOfBirth.isValidDate("")); // empty string
        assertFalse(DateOfBirth.isValidDate(" ")); // spaces only

        // invalid format
        assertFalse(DateOfBirth.isValidDate("29/02/2001321"));
        assertFalse(DateOfBirth.isValidDate("29/0232/2001"));
        assertFalse(DateOfBirth.isValidDate("291/02/2001"));
        assertFalse(DateOfBirth.isValidDate("29/022001"));
        assertFalse(DateOfBirth.isValidDate("2902/2001"));

        // invalid months with 31 days
        assertFalse(DateOfBirth.isValidDate("29/02/2001"));
        assertFalse(DateOfBirth.isValidDate("30/02/2001"));
        assertFalse(DateOfBirth.isValidDate("31/02/2001"));
        assertFalse(DateOfBirth.isValidDate("31/04/2001"));
        assertFalse(DateOfBirth.isValidDate("31/06/2001"));
        assertFalse(DateOfBirth.isValidDate("31/09/2001"));
        assertFalse(DateOfBirth.isValidDate("31/11/2001"));

        //invalid months
        assertFalse(DateOfBirth.isValidDate("15/15/2001"));
        assertFalse(DateOfBirth.isValidDate("15/00/2001"));

        // valid february dates
        assertTrue(DateOfBirth.isValidDate("28/02/2001"));
        assertTrue(DateOfBirth.isValidDate("29/02/2000"));

        // valid months with 31 days
        assertTrue(DateOfBirth.isValidDate("31/01/2000"));
        assertTrue(DateOfBirth.isValidDate("31/03/2000"));
        assertTrue(DateOfBirth.isValidDate("31/05/2000"));
        assertTrue(DateOfBirth.isValidDate("31/07/2000"));
        assertTrue(DateOfBirth.isValidDate("31/08/2000"));
        assertTrue(DateOfBirth.isValidDate("31/10/2000"));
        assertTrue(DateOfBirth.isValidDate("31/12/2000"));

        // valid months with 30 days
        assertTrue(DateOfBirth.isValidDate("30/01/2000"));
        assertTrue(DateOfBirth.isValidDate("31/03/2000"));
        assertTrue(DateOfBirth.isValidDate("30/04/2000"));
        assertTrue(DateOfBirth.isValidDate("30/05/2000"));
        assertTrue(DateOfBirth.isValidDate("30/06/2000"));
        assertTrue(DateOfBirth.isValidDate("30/07/2000"));
        assertTrue(DateOfBirth.isValidDate("30/08/2000"));
        assertTrue(DateOfBirth.isValidDate("30/09/2000"));
        assertTrue(DateOfBirth.isValidDate("30/09/2000"));
        assertTrue(DateOfBirth.isValidDate("30/10/2000"));
        assertTrue(DateOfBirth.isValidDate("30/11/2000"));
        assertTrue(DateOfBirth.isValidDate("30/12/2000"));
    }
}
