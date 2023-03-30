package seedu.address.model.client.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class MeetupDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetupDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new MeetupDate(invalidDate));
    }


    @Test
    public void isValidDate() {
        String date1 = "01.01.2023";
        String date2 = "01/01/2023";

        assertTrue(MeetupDate.isValidDate(date1));
        assertFalse(MeetupDate.isValidDate(date2));
    }

    @Test
    public void isFutureDate_returnsFalse() {
        String date1 = "01.01.2024";
        assertFalse(MeetupDate.isFutureDate(date1));
    }

    @Test
    public void isFutureDate_returnsTrue() {
        String date1 = "01.01.2020";
        assertTrue(MeetupDate.isFutureDate(date1));
    }
}
