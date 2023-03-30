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
        String date2 = "01/01/2023";

        assertTrue(CustomDate.isValidDate(date1));
        assertFalse(CustomDate.isValidDate(date2));
    }
}
