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

class UniqueEventListTest {
    private final UniqueEventList uniqueEventList = new UniqueEventList();

    @Test
    void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.contains(null));
    }

    @Test
    void contains_eventMissing_returnsFalse() {
        assertFalse(uniqueEventList.contains(EVENT_1));
    }

    @Test
    void contains_eventExists_returnsTrue() {
        uniqueEventList.add(EVENT_1);
        assertTrue(uniqueEventList.contains(EVENT_1));
        assertTrue(uniqueEventList.contains(EVENT_1.copy()));
    }

    @Test
    void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.add(null));
    }

    @Test
    void remove_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.remove(null));
    }

    @Test
    void remove_missingEvent_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.remove(EVENT_1));
    }

    @Test
    void remove_existingEvent_success() {
        uniqueEventList.add(EVENT_1);
        uniqueEventList.remove(EVENT_1);
        assertEquals(new UniqueEventList(), uniqueEventList);
    }

    @Test
    void setEvent_nullParameter_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(null, null));
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(Index.fromZeroBased(0), null));
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(null, EVENT_1));
    }

    @Test
    void setEvent_invalidIndex_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.setEvent(Index.fromZeroBased(0), EVENT_1));
    }

    @Test
    void setEvent_validIndex_success() {
        uniqueEventList.add(EVENT_1);
        uniqueEventList.setEvent(Index.fromZeroBased(0), EVENT_2);
        UniqueEventList expectedList = new UniqueEventList();
        expectedList.add(EVENT_2);
        assertEquals(expectedList, uniqueEventList);
    }

    @Test
    void setEvents_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((List<Event>) null));
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((UniqueEventList) null));
    }

    @Test
    void setEvents_uniqueEventList() {
        uniqueEventList.add(EVENT_1);
        UniqueEventList expectedList = new UniqueEventList();
        expectedList.add(EVENT_2);
        uniqueEventList.setEvents(expectedList);
        assertEquals(expectedList, uniqueEventList);
    }

    @Test
    void setEvents_listOfEvents() {
        uniqueEventList.add(EVENT_1);
        UniqueEventList expectedList = new UniqueEventList();
        expectedList.add(EVENT_2);
        uniqueEventList.setEvents(List.of(EVENT_2));
        assertEquals(expectedList, uniqueEventList);
    }

    @Test
    void getEvent_validIndex() {
        uniqueEventList.add(EVENT_1);
        uniqueEventList.add(EVENT_2);
        assertEquals(EVENT_1, uniqueEventList.getEvent(Index.fromZeroBased(0)));
        assertEquals(EVENT_2, uniqueEventList.getEvent(Index.fromZeroBased(1)));
    }

    @Test
    void getEvent_invalidIndex_throwsEventNotFoundException() {
        uniqueEventList.add(EVENT_1);
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.getEvent(Index.fromZeroBased(1)));
    }

    @Test
    void isPersonTaggedToEvent_invalidIndex_throwsEventNotFoundException() {
        uniqueEventList.add(EVENT_1);
        assertThrows(EventNotFoundException.class, () -> uniqueEventList
                .isPersonTaggedToEvent(Index.fromZeroBased(1), ALICE));
    }

    @Test
    void isPersonTaggedToEvent_validIndex() {
        uniqueEventList.add(EVENT_1.addTaggedPerson(ALICE).deleteTaggedPerson(BOB));
        Index index = Index.fromZeroBased(0);
        assertTrue(uniqueEventList.isPersonTaggedToEvent(index, ALICE));
        assertFalse(uniqueEventList.isPersonTaggedToEvent(index, BOB));
    }

    @Test
    void tagPersonToEvent_null_throwsNullPointerException() {
        Index index = Index.fromZeroBased(0);
        assertThrows(NullPointerException.class, () -> uniqueEventList.tagPersonToEvent(null, null));
        assertThrows(NullPointerException.class, () -> uniqueEventList.tagPersonToEvent(index, null));
        assertThrows(NullPointerException.class, () -> uniqueEventList.tagPersonToEvent(null, ALICE));
    }

    @Test
    void tagPersonToEvent_success() {
        Index index = Index.fromZeroBased(0);
        uniqueEventList.add(EVENT_1);
        uniqueEventList.tagPersonToEvent(index, ALICE);
        assertTrue(uniqueEventList.isPersonTaggedToEvent(index, ALICE));
    }

    @Test
    void untagPersonToEvent_null_throwsNullPointerException() {
        Index index = Index.fromZeroBased(0);
        assertThrows(NullPointerException.class, () -> uniqueEventList.untagPersonToEvent(null, null));
        assertThrows(NullPointerException.class, () -> uniqueEventList.untagPersonToEvent(index, null));
        assertThrows(NullPointerException.class, () -> uniqueEventList.untagPersonToEvent(null, ALICE));
    }

    @Test
    void untagPersonToEvent_success() {
        Index index = Index.fromZeroBased(0);
        uniqueEventList.add(EVENT_1);
        uniqueEventList.untagPersonToEvent(index, BOB);
        assertFalse(uniqueEventList.isPersonTaggedToEvent(index, BOB));
    }

    @Test
    void equalsTest() {
        uniqueEventList.add(EVENT_1);
        UniqueEventList expectedList = new UniqueEventList();
        expectedList.add(EVENT_1);
        assertEquals(expectedList, uniqueEventList);
        assertNotEquals(new UniqueEventList(), uniqueEventList);
    }
}
