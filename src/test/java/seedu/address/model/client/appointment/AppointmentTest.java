package seedu.address.model.client.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.policy.CustomDate;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.ClientBuilder;

public class AppointmentTest {

    private final Appointment DISCUSSION = new AppointmentBuilder().build();
    private final Appointment EMPTY = new AppointmentBuilder().emptyBuild();

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
    public void equals() {
        Appointment discCpy = new AppointmentBuilder(DISCUSSION).build();

        // same values -> returns true
        assertEquals(DISCUSSION, discCpy);

        // same object - > returns true
        assertEquals(DISCUSSION, DISCUSSION);

        // null -> returns false
        assertNotEquals(null, DISCUSSION);

        // different object -> returns false
        assertNotEquals(new ClientBuilder(ALICE).build(), DISCUSSION);

        // different appointment name -> returns false
        Appointment editedAppointment = new AppointmentBuilder(DISCUSSION).withAppointmentName("review goals").build();
        assertNotEquals(DISCUSSION, editedAppointment);

        // different meetup date -> returns false
        editedAppointment = new AppointmentBuilder(DISCUSSION).withMeetupDate("04.04.2050").build();
        assertNotEquals(DISCUSSION, editedAppointment);


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

    @Test
    public void testHashCode() {
        Appointment appt = new AppointmentBuilder(DISCUSSION).build();
        assertEquals(appt.hashCode(), DISCUSSION.hashCode());
    }

    @Test
    public void testToString() {
        String expectedMessage = "No appointment set";
        assertEquals(EMPTY.toString(), expectedMessage);

        expectedMessage = "Meetup discussion; Meetup Date: 01.01.2024";
        assertEquals(DISCUSSION.toString(), expectedMessage);
    }
}
