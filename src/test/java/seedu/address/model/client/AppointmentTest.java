package seedu.address.model.client;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppointmentTest {
    // to be fixed has issues
    /*
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null));
    }
    */

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidAppointment = "1999-23-30";
        assertThrows(IllegalArgumentException.class, () -> new Appointment(invalidAppointment));
    }
    // to be fixed has issues
    /*
    @Test
    public void isValidTagName() {
        assertThrows(NullPointerException.class, () -> Appointment.isValidAppointment("10-20-1992"));
    }
    */
}
