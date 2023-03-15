package seedu.address.model.fish;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LastFedDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LastFedDate(null));
    }

    @Test
    public void constructor_invalidLastFedDate_throwsIllegalArgumentException() {
        String invalidLastFedDate = "";
        assertThrows(IllegalArgumentException.class, () -> new LastFedDate(invalidLastFedDate));
    }

    @Test
    public void isValidLastFedDate() {
        // null date
        assertThrows(NullPointerException.class, () -> LastFedDate.isValidLastFedDate(null));

        // invalid date
        assertFalse(LastFedDate.isValidLastFedDate("")); // empty string
        assertFalse(LastFedDate.isValidLastFedDate(" ")); // spaces only
        assertFalse(LastFedDate.isValidLastFedDate("91")); // less than 3 numbers
        assertFalse(LastFedDate.isValidLastFedDate("01.01.2000")); // format is dd/mm/yyyy
        assertFalse(LastFedDate.isValidLastFedDate("1a/05/2000")); // alphabets within digits
        assertFalse(LastFedDate.isValidLastFedDate("32/01/2023")); // invalid date

        // valid dates
        assertTrue(LastFedDate.isValidLastFedDate("12/12/2000")); // exactly 3 numbers
        assertTrue(LastFedDate.isValidLastFedDate("05/05/2022"));
        assertTrue(LastFedDate.isValidLastFedDate("06/05/2021"));
    }
}
