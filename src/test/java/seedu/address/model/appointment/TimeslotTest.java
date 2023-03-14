package seedu.address.model.appointment;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeslotTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Timeslot(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidTimeslot = "";
        assertThrows(IllegalArgumentException.class, () -> new Timeslot(invalidTimeslot));
    }
}
