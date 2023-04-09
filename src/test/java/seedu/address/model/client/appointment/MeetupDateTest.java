package seedu.address.model.client.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.ClientBuilder.DEFAULT_EMPTY_APPOINTMENT;
import static seedu.address.testutil.TypicalClients.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.Client;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.ClientBuilder;


public class MeetupDateTest {

    private static final MeetupDate FUTURE = new MeetupDate(VALID_APPOINTMENT_DATE);
    private static final MeetupDate EMPTY = DEFAULT_EMPTY_APPOINTMENT.getMeetupDate();

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetupDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new MeetupDate(invalidDate));

        String pastDate = "01.01.2000";
        assertThrows(IllegalArgumentException.class, () -> new MeetupDate(pastDate));
    }

    @Test
    public void isValidDate() {
        // invalid date (wrong format) -> false
        assertFalse(MeetupDate.isValidDate("01/01/2023"));

        // invalid date (impossible days and months) -> false
        assertFalse(MeetupDate.isValidDate("31.04.2023"));
        assertFalse(MeetupDate.isValidDate("31.06.2023"));
        assertFalse(MeetupDate.isValidDate("32.06.2023"));
        assertFalse(MeetupDate.isValidDate("01.13.2023"));

        // invalid date with leap year -> false
        assertFalse(MeetupDate.isValidDate("29.02.2021"));
        assertFalse(MeetupDate.isValidDate("30.02.2021"));
        assertFalse(MeetupDate.isValidDate("31.02.2021"));

        // valid date -> true
        assertTrue(MeetupDate.isValidDate("29.02.2024"));
        assertTrue(MeetupDate.isValidDate("29.02.2028"));
    }

    @Test
    public void isFutureDate() {
        // future date -> true
        assertTrue(MeetupDate.isFutureDate("01.01.2024"));

        // past date -> false;
        assertFalse(MeetupDate.isFutureDate("01.01.2020"));
    }

    @Test
    public void getDisplayString_validDate_returnsString() {
        String displayed = FUTURE.getDisplayString();
        String expectedMessage = "May 5 2040";

        assertEquals(displayed, expectedMessage);
    }

    @Test
    public void getDisplayString_null_returnsNull() {
        String displayed = EMPTY.getDisplayString();

        assertNull(displayed);
    }

    @Test
    public void equals() {
        MeetupDate futureCpy = new MeetupDate(VALID_APPOINTMENT_DATE);
        MeetupDate emptyCpy = new AppointmentBuilder(DEFAULT_EMPTY_APPOINTMENT).build().getMeetupDate();
        Client alice = new ClientBuilder(ALICE).build();

        // same values -> return true
        assertEquals(FUTURE, futureCpy);

        // same object -> returns true
        assertEquals(FUTURE, FUTURE);

        // null -> returns false
        assertNotEquals(null, FUTURE);

        // different object -> returns false
        assertNotEquals(alice, FUTURE);

        // empty meetup date with empty meet up date -> returns true
        assertEquals(EMPTY, emptyCpy);

        // empty meetup date with a non-empty meetup date -> returns false
        assertNotEquals(EMPTY, FUTURE);
    }

    @Test
    void testHashCode() {
        MeetupDate futureCpy = new MeetupDate(VALID_APPOINTMENT_DATE);
        assertEquals(futureCpy.hashCode(), FUTURE.hashCode());
    }
}
