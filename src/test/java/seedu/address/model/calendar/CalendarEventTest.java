package seedu.address.model.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;

class CalendarEventTest {

    private final Person alice = new Person(new Name("Alice"), new Phone("91234567"),
            new Address("123, Jurong West Ave 6, #08-111"), new seedu.address.model.person.PayRate("10"),
            new Session("09-03-2023 12:00", "09-03-2023 13:00"), new HashSet<>());

    private final Person bob = new Person(new Name("Bob"), new Phone("91234568"),
            new Address("123, Jurong West Ave 6, #08-111"), new seedu.address.model.person.PayRate("11"),
            new Session("09-03-2023 14:00", "09-03-2023 15:00"), new HashSet<>());

    private final Person charlie = new Person(new Name("Charlie"), new Phone("91234569"),
            new Address("123, Jurong West Ave 6, #08-111"), new seedu.address.model.person.PayRate("12"),
            new Session("09-03-2023 12:00", "09-03-2023 13:00"), new HashSet<>());

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(alice.getCalendarEvents().get(0).equals(alice.getCalendarEvents().get(0)));

        // same values -> returns true
        assertTrue(alice.getCalendarEvents().get(0).equals(new CalendarEvent(alice)));

        // different types -> returns false
        assertFalse(alice.getCalendarEvents().get(0).equals(5));

        // null -> returns false
        assertFalse(alice.getCalendarEvents().get(0).equals(null));

        // different person -> returns false
        assertFalse(alice.getCalendarEvents().get(0).equals(new CalendarEvent(bob)));

        // different session -> returns false
        assertFalse(alice.getCalendarEvents().get(0).equals(new CalendarEvent(charlie)));
    }

    @Test
    public void compareTo() {
        CalendarEvent event1 = new CalendarEvent(alice);
        CalendarEvent event2 = new CalendarEvent(bob);

        // same session -> returns 0
        assertEquals(0, event1.compareTo(new CalendarEvent(alice)));

        // earlier session -> returns negative value
        assertTrue(event1.compareTo(event2) < 0);

        // later session -> returns positive value
        assertTrue(event2.compareTo(event1) > 0);
    }

    @Test
    public void getters() {
        CalendarEvent event = new CalendarEvent(alice);

        assertEquals(alice, event.getPerson());
        assertEquals(alice.getName(), event.getName());
        assertEquals(alice.getAddress(), event.getAddress());
        assertEquals(alice.getSession().toString(), event.getDate());
        assertEquals(alice.getSession().getDay(), event.getDay());
        assertEquals(alice.getSession().getMonth(), event.getMonth());
        assertEquals(alice.getSession().getYear(), event.getYear());
        assertEquals(alice.getSession().getTimeFormat(), event.getTimeFormat());
    }

    @Test
    public void hashCode_sameObject_sameHashcode() {
        CalendarEvent event = new CalendarEvent(alice);
        assertEquals(event.hashCode(), event.hashCode());
    }
}
