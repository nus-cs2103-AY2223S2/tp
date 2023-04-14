package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, null));
    }

    @Test
    public void constructor_reorderedTime_throwsIllegalArgumentException() {
        LocalDateTime startTime = LocalDateTime.of(2105, 7, 1, 14, 30);
        LocalDateTime endTime = LocalDateTime.of(2105, 7, 1, 10, 30);
        assertThrows(IllegalArgumentException.class, () -> new Appointment(startTime, endTime));
    }

    @Test
    public void constructor_notOnSameDate_throwsIllegalArgumentException() {
        LocalDateTime startTime = LocalDateTime.of(2105, 7, 1, 14, 30);
        LocalDateTime endTime = LocalDateTime.of(2105, 7, 2, 10, 30);
        assertThrows(IllegalArgumentException.class, () -> new Appointment(startTime, endTime));
    }

    @Test
    public void isValidAge() {
        // null age
        assertThrows(NullPointerException.class, () -> Appointment.isValidAppointment(null, null));

        LocalDateTime time1 = LocalDateTime.of(2105, 7, 1, 14, 30);
        LocalDateTime time2 = LocalDateTime.of(2105, 7, 1, 10, 30);
        LocalDateTime time3 = LocalDateTime.of(2105, 7, 2, 10, 30);
        LocalDateTime time4 = LocalDateTime.of(2105, 7, 1, 16, 30);
        // invalid appointments
        assertFalse(Appointment.isValidAppointment(time1, time2)); // non-number string
        assertFalse(Appointment.isValidAppointment(time1, time3)); // spaces only

        // valid appointments
        assertTrue(Appointment.isValidAppointment(time1, time4)); // spaces only

    }
}
