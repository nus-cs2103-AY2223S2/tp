package ezschedule.model.event;

import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_B;
import static ezschedule.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ezschedule.model.event.exceptions.InvalidDateException;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void constructor_invalidDate_throwsInvalidDateException() {
        String invalidDate = "2023-02-31"; // date do not exist
        assertThrows(InvalidDateException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid dates
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only

        assertFalse(Date.isValidDate("2023-06")); // less than 3 numbers
        assertFalse(Date.isValidDate("twenty-twenty-three")); // non-numeric
        assertFalse(Date.isValidDate("t2023-06-02")); // alphabets within digits
        assertFalse(Date.isValidDate("2023 06 02")); // no dashes

        assertFalse(Date.isValidDate("0000-01-01")); // year do not start from 0001
        assertFalse(Date.isValidDate("2023-00-01")); // months not within 01 to 12
        assertFalse(Date.isValidDate("2023-13-01")); // months not within 01 to 12
        assertFalse(Date.isValidDate("2023-06-00")); // days not within 01 to 31
        assertFalse(Date.isValidDate("2023-06-32")); // days not within 01 to 31

        // valid dates
        assertTrue(Date.isValidDate("2023-06-02")); // exactly 3 numbers
        assertTrue(Date.isValidDate("0001-06-02")); // year start from 0001
        assertTrue(Date.isValidDate("2023-01-02")); // months start from 01
        assertTrue(Date.isValidDate("2023-12-02")); // months ends at 12
        assertTrue(Date.isValidDate("2023-06-01")); // days start from 01
        assertTrue(Date.isValidDate("2023-06-31")); // days end at 31
    }

    @Test
    public void getYear() {
        Date date = new Date("2023-06-02");
        assertEquals(2023, date.getYear()); // same year -> returns equal
        assertNotEquals(2020, date.getYear()); // earlier year - returns not equal
        assertNotEquals(2025, date.getYear()); // later year - returns not equal
    }

    @Test
    public void getMonth() {
        Date date = new Date("2023-06-02");
        assertEquals(6, date.getMonth()); // same month -> returns equal
        assertNotEquals(1, date.getMonth()); // earlier month - returns not equal
        assertNotEquals(12, date.getMonth()); // later month - returns not equal
    }

    @Test
    public void getDay() {
        Date date = new Date("2023-06-02");
        assertEquals(2, date.getDay()); // same day -> returns equal
        assertNotEquals(1, date.getDay()); // earlier day - returns not equal
        assertNotEquals(31, date.getDay()); // later day - returns not equal
    }

    @Test
    public void equal() {
        // same object -> returns equal
        Date date = new Date(VALID_DATE_A);
        assertEquals(date, date);

        // same values -> returns equal
        Date dateCopy = new Date(VALID_DATE_A);
        assertEquals(date, dateCopy);

        // null -> returns not equal
        assertNotEquals(null, date);

        // different type -> returns not equal
        assertNotEquals(5, date);

        // different date -> returns not equal
        Date differentDate = new Date(VALID_DATE_B);
        assertNotEquals(differentDate, date);
    }
}
