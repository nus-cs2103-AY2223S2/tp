package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.EVENT_1;
import static seedu.address.testutil.TypicalEvents.EVENT_2;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.event.exceptions.EventNotFoundException;

class EventListTest {
    private final EventList eventList = new EventList();

    @Test
    void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventList.contains(null));
    }

    @Test
    void contains_eventMissing_returnsFalse() {
        assertFalse(eventList.contains(EVENT_1));
    }

    @Test
    void contains_eventExists_returnsTrue() {
        eventList.add(EVENT_1);
        assertTrue(eventList.contains(EVENT_1));
        assertTrue(eventList.contains(EVENT_1.copy()));
    }

    @Test
    void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventList.add(null));
    }

    @Test
    void remove_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventList.remove(null));
    }

    @Test
    void remove_missingEvent_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> eventList.remove(EVENT_1));
    }

    @Test
    void remove_existingEvent_success() {
        eventList.add(EVENT_1);
        eventList.remove(EVENT_1);
        assertEquals(new EventList(), eventList);
    }

    @Test
    void setEvent_nullParameter_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventList.setEvent(null, null));
        assertThrows(NullPointerException.class, () -> eventList.setEvent(Index.fromZeroBased(0), null));
        assertThrows(NullPointerException.class, () -> eventList.setEvent(null, EVENT_1));
    }

    @Test
    void setEvent_invalidIndex_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> eventList.setEvent(Index.fromZeroBased(0), EVENT_1));
    }

    @Test
    void setEvent_validIndex_success() {
        eventList.add(EVENT_1);
        eventList.setEvent(Index.fromZeroBased(0), EVENT_2);
        EventList expectedList = new EventList();
        expectedList.add(EVENT_2);
        assertEquals(expectedList, eventList);
    }

    @Test
    void setEvents_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventList.setEvents((List<Event>) null));
        assertThrows(NullPointerException.class, () -> eventList.setEvents((EventList) null));
    }

    @Test
    void setEvents_uniqueEventList() {
        eventList.add(EVENT_1);
        EventList expectedList = new EventList();
        expectedList.add(EVENT_2);
        eventList.setEvents(expectedList);
        assertEquals(expectedList, eventList);
    }

    @Test
    void setEvents_listOfEvents() {
        eventList.add(EVENT_1);
        EventList expectedList = new EventList();
        expectedList.add(EVENT_2);
        eventList.setEvents(List.of(EVENT_2));
        assertEquals(expectedList, eventList);
    }

    @Test
    void getEvent_validIndex() {
        eventList.add(EVENT_1);
        eventList.add(EVENT_2);
        assertEquals(EVENT_1, eventList.getEvent(Index.fromZeroBased(0)));
        assertEquals(EVENT_2, eventList.getEvent(Index.fromZeroBased(1)));
    }

    @Test
    void getEvent_invalidIndex_throwsEventNotFoundException() {
        eventList.add(EVENT_1);
        assertThrows(EventNotFoundException.class, () -> eventList.getEvent(Index.fromZeroBased(1)));
    }

    @Test
    void isPersonTaggedToEvent_invalidIndex_throwsEventNotFoundException() {
        eventList.add(EVENT_1);
        assertThrows(EventNotFoundException.class, () -> eventList
                .isPersonTaggedToEvent(Index.fromZeroBased(1), ALICE));
    }

    @Test
    void isPersonTaggedToEvent_validIndex() {
        eventList.add(EVENT_1.addTaggedPerson(ALICE).deleteTaggedPerson(BOB));
        Index index = Index.fromZeroBased(0);
        assertTrue(eventList.isPersonTaggedToEvent(index, ALICE));
        assertFalse(eventList.isPersonTaggedToEvent(index, BOB));
    }

    @Test
    void tagPersonToEvent_null_throwsNullPointerException() {
        Index index = Index.fromZeroBased(0);
        assertThrows(NullPointerException.class, () -> eventList.tagPersonToEvent(null, null));
        assertThrows(NullPointerException.class, () -> eventList.tagPersonToEvent(index, null));
        assertThrows(NullPointerException.class, () -> eventList.tagPersonToEvent(null, ALICE));
    }

    @Test
    void tagPersonToEvent_success() {
        Index index = Index.fromZeroBased(0);
        eventList.add(EVENT_1);
        eventList.tagPersonToEvent(index, ALICE);
        assertTrue(eventList.isPersonTaggedToEvent(index, ALICE));
    }

    @Test
    void untagPersonToEvent_null_throwsNullPointerException() {
        Index index = Index.fromZeroBased(0);
        assertThrows(NullPointerException.class, () -> eventList.untagPersonToEvent(null, null));
        assertThrows(NullPointerException.class, () -> eventList.untagPersonToEvent(index, null));
        assertThrows(NullPointerException.class, () -> eventList.untagPersonToEvent(null, ALICE));
    }

    @Test
    void untagPersonToEvent_success() {
        Index index = Index.fromZeroBased(0);
        eventList.add(EVENT_1);
        eventList.untagPersonToEvent(index, BOB);
        assertFalse(eventList.isPersonTaggedToEvent(index, BOB));
    }

    @Test
    void equalsTest() {
        eventList.add(EVENT_1);
        EventList expectedList = new EventList();
        expectedList.add(EVENT_1);
        assertEquals(expectedList, eventList);
        assertNotEquals(new EventList(), eventList);
    }
}
