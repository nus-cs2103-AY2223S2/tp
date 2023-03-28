package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_TIME_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_TIME_CARNIVAL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.CARNIVAL;
import static seedu.address.testutil.TypicalEvents.SPORTS_DAY;
import static seedu.address.testutil.TypicalEvents.WEDDING_DINNER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.testutil.EventBuilder;

public class UniqueEventListTest {

    private final UniqueEventList uniqueEventList = new UniqueEventList();

    @Test
    public void contains_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.contains(null));
    }

    @Test
    public void contains_eventNotInList_returnsFalse() {
        assertFalse(uniqueEventList.contains(CARNIVAL));
    }

    @Test
    public void contains_eventInList_returnsTrue() {
        uniqueEventList.add(CARNIVAL);
        assertTrue(uniqueEventList.contains(CARNIVAL));
    }

    @Test
    public void contains_eventWithSameIdentityFieldsInList_returnsFalse() {
        uniqueEventList.add(CARNIVAL);
        Event editedCarnival = new EventBuilder(CARNIVAL).withName(VALID_EVENT_NAME_CARNIVAL)
                .withStartDateTime(VALID_START_DATE_TIME_CARNIVAL)
                .withEndDateTime(VALID_END_DATE_TIME_CARNIVAL)
                .build();
        assertFalse(uniqueEventList.contains(editedCarnival));
    }

    @Test
    public void add_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.add(null));
    }

    @Test
    public void setEvents_nullUniqueEventList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((UniqueEventList) null));
    }

    @Test
    public void setEvents_uniqueEventList_replacesOwnListWithProvidedUniqueEventList() {
        uniqueEventList.add(WEDDING_DINNER);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(CARNIVAL);
        uniqueEventList.setEvents(expectedUniqueEventList);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((List<Event>) null));
    }

    @Test
    public void setEvents_list_replacesOwnListWithProvidedList() {
        uniqueEventList.add(WEDDING_DINNER);
        List<Event> eventList = Collections.singletonList(CARNIVAL);
        uniqueEventList.setEvents(eventList);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(CARNIVAL);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEventWithAnotherEventList_returnsTrue() {
        uniqueEventList.add(CARNIVAL);
        List<Event> events = new ArrayList<>(Arrays.asList(CARNIVAL));
        UniqueEventList other = new UniqueEventList();
        other.setEvents(events);
        assertTrue(uniqueEventList.equals(other));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEventList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void setNullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((UniqueEventList) null));
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((List<Event>) null));
    }

    @Test
    public void setEventWithAnotherUniqueEventList_returnsTrue() {
        uniqueEventList.add(CARNIVAL);
        UniqueEventList other = new UniqueEventList();
        other.setEvents(uniqueEventList);
        assertTrue(uniqueEventList.equals(other));
    }

    @Test
    public void setDuplicateEvent_throwsDuplicateEventException() {
        List<Event> invalidEvents = new ArrayList<>(Arrays.asList(CARNIVAL, CARNIVAL));
        assertThrows(DuplicateEventException.class, () -> new UniqueEventList().setEvents(invalidEvents));
    }

    @Test
    public void remove_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.remove(null));
    }

    @Test
    public void remove_eventDoesNotExist_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.remove(CARNIVAL));
    }

    @Test
    public void remove_existingEvent_removesEvent() {
        uniqueEventList.add(CARNIVAL);
        uniqueEventList.remove(CARNIVAL);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void eventsAreUnique_noEvents_returnsTrue() {
        List<Event> events = new ArrayList<>();
        assertTrue(uniqueEventList.eventsAreUnique(events));
    }

    @Test
    public void eventsAreUnique_sameEventExist_returnsFalse() {
        List<Event> events = new ArrayList<>(Arrays.asList(CARNIVAL, CARNIVAL));
        assertFalse(uniqueEventList.eventsAreUnique(events));
    }

    @Test
    public void eventsAreUnique_uniqueEvents_returnsTrue() {
        List<Event> events = new ArrayList<>(Arrays.asList(CARNIVAL, WEDDING_DINNER));
        assertTrue(uniqueEventList.eventsAreUnique(events));
    }

    @Test
    public void setEvent_targetEventNotInList_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.setEvent(CARNIVAL, CARNIVAL));
    }

    @Test
    public void setEvent_editedEventHasNonUniqueIdentity_throwsDuplicateEventException() {
        uniqueEventList.add(CARNIVAL);
        uniqueEventList.add(SPORTS_DAY);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvent(CARNIVAL, SPORTS_DAY));
    }
}
