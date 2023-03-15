package seedu.vms.model.patient;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.vms.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.parser.exceptions.ParseException;

public class DobTest {

    @Test
    public void constructor_invalidDob_throwsIllegalArgumentException() {
        String invalidDob = "";
        assertThrows(IllegalArgumentException.class, () -> new Dob(invalidDob));
    }

    @Test
    public void isValidDob() {
        // invalid dobs
        assertFalse(Dob.isValidDob("")); // empty string
        assertFalse(Dob.isValidDob(" ")); // spaces only
        assertFalse(Dob.isValidDob(LocalDateTime.now().plusDays(1))); // future dob
        assertFalse(Dob.isValidDob("2020-22-01")); // Invalid format

        // valid dobs
        assertTrue(Dob.isValidDob(LocalDateTime.now().minusDays(1))); // yesterday dob
        assertTrue(Dob.isValidDob("2020-02-29")); // Leap
        assertTrue(Dob.isValidDob("1903-05-29")); // Very old person
        assertTrue(Dob.isValidDob("2000-01-01")); // Y2k
    }

    @Test
    public void constructor_validDobString() throws ParseException {
        final String expected = "2020-03-19";

        assertEquals(expected, new Dob(expected).toString());
    }

    @Test
    public void constructor_validDobLocalDateTime() {
        LocalDateTime expected = LocalDateTime.now().minusDays(1);
        String expectedString = expected.format(ISO_LOCAL_DATE);

        assertEquals(expectedString, new Dob(expected).toString());
    }

    @Test
    public void isEqualsDob() throws ParseException {
        LocalDateTime expected = LocalDateTime.now().minusDays(1);
        String expectedString = expected.format(ISO_LOCAL_DATE);
        Dob equalOne = new Dob(expected);
        Dob equalTwo = new Dob(expected);
        Dob equalThree = new Dob(expectedString);
        Dob notEqual = new Dob("2020-03-23");

        // Equal Dobs
        assertTrue(equalOne.equals(equalTwo));
        assertTrue(equalOne.equals(equalThree));

        // Not Equal Dobs
        assertFalse(notEqual.equals(equalOne));
        assertFalse(notEqual.equals(equalThree));
        assertFalse(equalOne.equals(notEqual));
        assertFalse(equalThree.equals(notEqual));
    }
}
