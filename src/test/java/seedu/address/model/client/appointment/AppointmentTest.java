package seedu.address.model.client.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.policy.CustomDate;

public class AppointmentTest {


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
    void isValidDate() {
        String date1 = "01.01.2023";
        String date2 = "01.13.2023";
        String date3 = "29.02.2021";
        String date4 = "29/02/2021";
        String date5 = "2022/01/01";
        assertTrue(CustomDate.isValidDate(date1));
        assertFalse(CustomDate.isValidDate(date2));
        assertFalse(CustomDate.isValidDate(date3));
        assertFalse(CustomDate.isValidDate(date4));
        assertFalse(CustomDate.isValidDate(date5));
    }
}
