package seedu.fitbook.model.client;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.testutil.Assert.assertThrows;

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
        assertThrows(NullPointerException.class, () -> Appointment.isValidAppointment("10-10-1992"));
    }
    */

    @Test
    public void test_equalsSymmetric() {
        Appointment appointmentA = new Appointment("13-12-2200 18:00");
        Appointment appointmentB = new Appointment("13-12-2200 18:00");
        assertTrue(appointmentA.equals(appointmentB) && appointmentB.equals(appointmentA));
        assertTrue(appointmentA.hashCode() == appointmentB.hashCode());
    }

}
