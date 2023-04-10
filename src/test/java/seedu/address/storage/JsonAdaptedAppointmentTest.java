package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.DISCUSSION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.appointment.AppointmentName;

public class JsonAdaptedAppointmentTest {

    private static final String INVALID_APPOINTMENT_NAME = "#Meetup discussion";
    private static final String INVALID_MEETUP_DATE = "10-10-2023";

    private static final String VALID_APPOINTMENT_NAME = DISCUSSION.getAppointmentName().toString();
    private static final String VALID_MEETUP_DATE = DISCUSSION.getMeetupDate().toString();

    @Test
    public void toModelType_validAppointment_returnsAppointment() throws IllegalValueException {
        JsonAdaptedAppointment appt = new JsonAdaptedAppointment(DISCUSSION);
        assertEquals(DISCUSSION, appt.toModelType());
    }
    @Test
    public void toModelType_invalidAppointmentName_throwsIllegalValueException() {
        JsonAdaptedAppointment appt = new JsonAdaptedAppointment(INVALID_APPOINTMENT_NAME, VALID_MEETUP_DATE);
        String expectedMessage = AppointmentName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appt::toModelType);
    }

    @Test
    public void toModelType_nullAppointmentName_throwsIllegalValidException() {
        JsonAdaptedAppointment appt = new JsonAdaptedAppointment(null, VALID_MEETUP_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AppointmentName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appt::toModelType);
    }
}
