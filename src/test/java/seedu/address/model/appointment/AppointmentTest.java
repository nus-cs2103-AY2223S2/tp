package seedu.address.model.appointment;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class AppointmentTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Appointment appointment = new AppointmentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> appointment.getTags().remove(0));
    }
}
