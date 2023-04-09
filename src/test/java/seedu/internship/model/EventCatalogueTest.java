package seedu.internship.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalEvents.EM11;
import static seedu.internship.testutil.TypicalEvents.EM21;
import static seedu.internship.testutil.TypicalEvents.VALID_END_EM11;
import static seedu.internship.testutil.TypicalEvents.VALID_EVENT_DESCRIPTION_EM11;
import static seedu.internship.testutil.TypicalEvents.VALID_INTERNSHIP_EM11;
import static seedu.internship.testutil.TypicalEvents.VALID_NAME_EM11;
import static seedu.internship.testutil.TypicalEvents.VALID_START_EM11;
import static seedu.internship.testutil.TypicalEvents.getTypicalEventCatalogue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.exceptions.DuplicateEventException;
import seedu.internship.testutil.EventBuilder;


public class EventCatalogueTest {
    private final EventCatalogue eventCatalogue = new EventCatalogue();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), eventCatalogue.getEventList());
    }


    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventCatalogue.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEventCatalogue_replacesData() {
        EventCatalogue newData = getTypicalEventCatalogue();
        eventCatalogue.resetData(newData);
        assertEquals(newData, eventCatalogue);
    }

    @Test
    public void resetData_withDuplicateEvents_throwsDuplicateEventException() {
        // Two events with the same identity fields
        //Needs to be changed when we have valid event feilds
        Event editedEM11 = new EventBuilder()
                .withName(VALID_NAME_EM11)
                .withStart(VALID_START_EM11)
                .withEnd(VALID_END_EM11)
                .withDescription(VALID_EVENT_DESCRIPTION_EM11)
                .withInternship(VALID_INTERNSHIP_EM11).build();

        List<Event> newEvents = Arrays.asList(EM11, editedEM11);
        EventCatalogueTest.EventCatalogueStub newData = new EventCatalogueTest.EventCatalogueStub(newEvents);
    }

    public void resetData_withDuplicateInterviewEvents_throwsDuplicateEventException() {
        Event event = new EventBuilder().withName("e1").withStart("14/04/2023 1200").withEnd("14/04/2023 1500")
                .withDescription("").build();
        Event duplicate = new EventBuilder().withName("e1").withStart("14/04/2023 1200").withEnd("14/04/2023 1500")
                .withDescription("").build();

        List<Event> newEvents = Arrays.asList(event, duplicate);
        EventCatalogueStub newData = new EventCatalogueStub(newEvents);


        assertThrows(DuplicateEventException.class, () -> eventCatalogue.resetData(newData));
    }

    public void resetData_withDuplicateDeadlineEvents_throwsDuplicateEventException() {
        Event event = new EventBuilder().withName("d1").withStart("14/04/2023 2359").withEnd("14/04/2023 2359")
                .withDescription("").build();
        Event duplicate = new EventBuilder().withName("d1").withStart("14/04/2023 2359").withEnd("14/04/2023 2359")
                .withDescription("").build();

        List<Event> newEvents = Arrays.asList(event, duplicate);
        EventCatalogueStub newData = new EventCatalogueStub(newEvents);

        assertThrows(DuplicateEventException.class, () -> eventCatalogue.resetData(newData));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventCatalogue.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInEventCatalogue_returnsFalse() {
        assertFalse(eventCatalogue.hasEvent(EM11));
    }

    @Test
    public void hasEvent_eventInEventCatalogue_returnsTrue() {
        eventCatalogue.addEvent(EM21);
        assertTrue(eventCatalogue.hasEvent(EM21));
    }

    @Test
    public void setEvent_nullEvent_throwsNullPointerException() {
        eventCatalogue.addEvent(EM11);
        assertThrows(NullPointerException.class, () -> eventCatalogue.setEvent(EM11, null));
    }

    @Test
    public void setEvent_modifiedEvent_replacesEvent() {
        Event modified = new EventBuilder().withName("event1").withStart("15/04/2023 2359").withEnd("15/04/2023 2359")
                        .withDescription("").build();
        eventCatalogue.addEvent(EM11);
        eventCatalogue.setEvent(EM11, modified);
        assertTrue(eventCatalogue.hasEvent(modified));
        assertTrue(!eventCatalogue.hasEvent(EM11));
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> eventCatalogue.getEventList().remove(0));
    }

    /**
     * A stub ReadOnlyEventCatalogue whose events list can violate interface constraints.
     */
    private static class EventCatalogueStub implements ReadOnlyEventCatalogue {
        private final ObservableList<Event> events = FXCollections.observableArrayList();

        EventCatalogueStub(Collection<Event> events) {
            this.events.setAll(events);
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }
    }
}
