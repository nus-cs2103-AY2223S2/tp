package seedu.address.model.person.information;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class AvailableDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AvailableDate(null, null));
    }

    @Test
    public void constructor_invalidAvailableDates_throwsIllegalArgumentException() {
        String invalidStartDate = "random";
        String invalidEndDate = "random1";
        assertThrows(IllegalArgumentException.class, () ->
                new AvailableDate(invalidStartDate, invalidEndDate));
    }

    @Test
    public void isValidDate() {
        // null address
        assertThrows(NullPointerException.class, () -> AvailableDate.isValidDate(null, null));

        // invalid birth date
        assertFalse(AvailableDate.isValidDate("")); // empty string
        assertFalse(AvailableDate.isValidDate(" ")); // spaces only
        assertFalse(AvailableDate.isValidDate("hello")); // random letters
        assertFalse(AvailableDate.isValidDate("23-03-2020")); // not correct format
        assertFalse(AvailableDate.isValidDate("2020-02-21", "23-03-2020")); // one fails


        // valid birth date
        assertTrue(AvailableDate.isValidDate("2023-03-23"));
        assertTrue(AvailableDate.isValidDate("2000-12-28", "1955-05-17"));
    }


}
