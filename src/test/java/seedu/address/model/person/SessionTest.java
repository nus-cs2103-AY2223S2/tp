package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;





public class SessionTest {
    private Session session1;
    private Session session2;

    @BeforeEach
    public void setUp() {
        session1 = new Session("01-01-2022 10:00", "01-01-2022 12:00");
        session2 = new Session("02-01-2022 10:00", "02-01-2022 11:00");
    }

    @Test
    public void constructor_validSession_success() {
        assertEquals("01-01-2022 10:00", session1.getStartDateTime());
        assertEquals("01-01-2022 12:00", session1.getEndDateTime());
    }

    @Test
    public void constructor_validInput_success() throws IllegalValueException {
        Session session = new Session("05-09-2022 14:00 to 05-09-2022 15:00");
        assertEquals("05-09-2022 14:00", session.getStartDateTime());
        assertEquals("05-09-2022 15:00", session.getEndDateTime());
    }

    @Test
    public void constructor_invalidInput_exceptionThrown() {
        assertThrows(IllegalValueException.class, () -> new Session("05-09-2022 14:00"));
        assertThrows(IllegalValueException.class, () -> new Session("05-09-2022 14:00 05-09-2022 15:00"));
        assertThrows(IllegalValueException.class, () -> new Session("5-9-2022 14:00 to 5-9-2022 15:00"));
        assertThrows(IllegalValueException.class, () -> new Session("05-09-2022 25:00 to 05-09-2022 26:00"));
    }

    @Test
    public void isSameSession() {
        assertTrue(session1.isSameSession(session1));
        assertFalse(session1.isSameSession(null));
        assertFalse(session1.isSameSession(session2));
    }

    @Test
    public void equals() {
        Session session1Copy = new Session("01-01-2022 10:00", "01-01-2022 12:00");
        assertTrue(session1.equals(session1Copy));
        assertFalse(session1.equals(null));
        assertFalse(session1.equals(session2));
    }

    @Test
    public void getSessionDuration() {
        assertEquals(Duration.ofHours(2), session1.getSessionDuration());
        assertEquals(Duration.ofHours(1), session2.getSessionDuration());
    }

    @Test
    public void isValidSession() {
        assertTrue(session1.isValidSession());
        assertTrue(session2.isValidSession());
        assertThrows(IllegalArgumentException.class, () -> new Session("01-01-2022 12:00", "01-01-2022 10:00"));
    }


    @Test
    public void getCommand() {
        assertEquals("01-01-2022 10:00 to 01-01-2022 12:00", session1.getCommand());
        assertEquals("02-01-2022 10:00 to 02-01-2022 11:00", session2.getCommand());
    }
}
