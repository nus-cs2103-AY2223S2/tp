package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ALICE;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.testutil.EventBuilder;

public class EventBookTest {

    private final EventBook eventBook = new EventBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), eventBook.getEventList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEventBook_replacesData() {
        EventBook newData = getTypicalEventBook();
        eventBook.resetData(newData);
        assertEquals(newData, eventBook);
    }

    @Test
    public void resetData_withDuplicateEvents_throwsDuplicateEventException() {
        // Two events with the same identity fields
        Event editedAlice = new EventBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Event> newEvents = Arrays.asList(ALICE, editedAlice);
        EventBookStub newData = new EventBookStub(newEvents);

        assertThrows(DuplicateEventException.class, () -> eventBook.resetData(newData));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventBook.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInEventBook_returnsFalse() {
        assertFalse(eventBook.hasEvent(ALICE));
    }

    @Test
    public void hasEvent_eventInEventBook_returnsTrue() {
        eventBook.addEvent(ALICE);
        assertTrue(eventBook.hasEvent(ALICE));
    }

    @Test
    public void hasEvent_eventWithSameIdentityFieldsInEventBook_returnsTrue() {
        eventBook.addEvent(ALICE);
        Event editedAlice = new EventBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(eventBook.hasEvent(editedAlice));
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> eventBook.getEventList().remove(0));
    }

    @Test
    public void linkContact_linkContactToEvent_success() {
        eventBook.addEvent(ALICE);
        Event editedAlice = new EventBuilder(ALICE).withContact("ALICE", "91234567").build();
        eventBook.linkContact(ALICE, editedAlice);
        assertTrue(eventBook.hasEvent(editedAlice));
    }

    @Test
    public void linkContact_linkNullToEvent_throwsNullPointerException() {
        eventBook.addEvent(ALICE);
        assertThrows(NullPointerException.class, () -> eventBook.linkContact(ALICE, null));
    }

    /**
     * A stub ReadOnlyEventBook whose events list can violate interface constraints.
     */
    private static class EventBookStub implements ReadOnlyEventBook {
        private final ObservableList<Event> events = FXCollections.observableArrayList();

        EventBookStub(Collection<Event> events) {
            this.events.setAll(events);
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }
    }

}
