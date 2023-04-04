package ezschedule.model;

import static ezschedule.logic.commands.CommandTestUtil.VALID_START_TIME_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_START_TIME_B;
import static ezschedule.testutil.Assert.assertThrows;
import static ezschedule.testutil.TypicalEvents.ART;
import static ezschedule.testutil.TypicalEvents.BOAT;
import static ezschedule.testutil.TypicalEvents.CARNIVAL;
import static ezschedule.testutil.TypicalEvents.DRAG;
import static ezschedule.testutil.TypicalEvents.getTypicalScheduler;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ezschedule.model.event.Event;
import ezschedule.model.event.exceptions.DuplicateEventException;
import ezschedule.testutil.EventBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SchedulerTest {

    private final Scheduler scheduler = new Scheduler();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), scheduler.getEventList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduler.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyScheduler_replacesData() {
        Scheduler newData = getTypicalScheduler();
        scheduler.resetData(newData);
        assertEquals(newData, scheduler);
    }

    @Test
    public void resetData_withDuplicateEvents_throwsDuplicateEventException() {
        // Two events with the same identity fields
        Event editedA = new EventBuilder(ART).build();
        Event editedB = new EventBuilder(ART).build();

        List<Event> newEvents = Arrays.asList(editedA, editedB);
        SchedulerStub newData = new SchedulerStub(newEvents);

        assertThrows(DuplicateEventException.class, () -> scheduler.resetData(newData));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduler.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInScheduler_returnsFalse() {
        assertFalse(scheduler.hasEvent(ART));
    }

    @Test
    public void hasEvent_eventInScheduler_returnsTrue() {
        scheduler.addEvent(ART);
        assertTrue(scheduler.hasEvent(ART));
    }

    @Test
    public void hasEvent_eventWithSameIdentityFieldsInScheduler_returnsTrue() {
        scheduler.addEvent(ART);
        Event editedA = new EventBuilder(ART).withStartTime(VALID_START_TIME_A).build();
        assertTrue(scheduler.hasEvent(editedA));
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> scheduler.getEventList().remove(0));
    }

    @Test
    public void autoSortEvents_duringAdd_eventsInChronologicalOrder() {
        Model expectedModel;

        /* --- Test adding events with different dates --- */

        expectedModel = new ModelManager();

        expectedModel.addEvent(DRAG);
        expectedModel.addEvent(BOAT);
        assertEquals(Arrays.asList(BOAT, DRAG), expectedModel.getEventList());

        expectedModel.addEvent(CARNIVAL);
        assertEquals(Arrays.asList(BOAT, CARNIVAL, DRAG), expectedModel.getEventList());

        expectedModel.addEvent(ART);
        assertEquals(Arrays.asList(ART, BOAT, CARNIVAL, DRAG), expectedModel.getEventList());

        /* --- Test adding events with date but different start times --- */

        Event e1 = new EventBuilder().withStartTime(VALID_START_TIME_A).build();
        Event e2 = new EventBuilder().withStartTime(VALID_START_TIME_B).build();

        expectedModel = new ModelManager();
        expectedModel.addEvent(e2);
        expectedModel.addEvent(e1);
        assertEquals(Arrays.asList(e1, e2), expectedModel.getEventList());

        /* --- Test adding events of "opposite" end time --- */
        // Test sorting of events is correct even when the "later" event ends earlier than the "earlier" event.
        // Note: theoretically, such event combination should not be allowed (due to partial overlap of time),
        //       but for the sake of testing sorting correctness, it is done here.

        Event e3 = new EventBuilder().withStartTime(VALID_START_TIME_A).withEndTime("21:00").build();
        Event e4 = new EventBuilder().withStartTime(VALID_START_TIME_B).withEndTime("20:00").build();

        expectedModel = new ModelManager();
        expectedModel.addEvent(e4);
        expectedModel.addEvent(e3);
        assertEquals(Arrays.asList(e3, e4), expectedModel.getEventList());
    }

    @Test
    public void autoSortEvent_afterEdit_eventsInChronologicalOrder() {
        Model model = new ModelManager(getTypicalScheduler(), new UserPrefs());

        // "edit" the event by changing the date
        model.setEvent(CARNIVAL, new EventBuilder(CARNIVAL).withDate("2023-04-30").build());

        String[] actualOrder = getEventNameFrom(model.getEventList());
        String[] expectedOrder = new String[]{
                CARNIVAL.getName().toString(),
                ART.getName().toString(),
                BOAT.getName().toString(),
                DRAG.getName().toString(),
        };

        assertArrayEquals(expectedOrder, actualOrder);
    }



    /**
     * A stub ReadOnlyScheduler whose events list can violate interface constraints.
     */
    private static class SchedulerStub implements ReadOnlyScheduler {
        private final ObservableList<Event> events = FXCollections.observableArrayList();

        SchedulerStub(Collection<Event> events) {
            this.events.setAll(events);
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }
    }

    private static String[] getEventNameFrom(List<Event> events) {
        ArrayList<String> arr = new ArrayList<>();

        for (Event e : events) {
            arr.add(e.getName().toString());
        }

        String[] strArr = new String[arr.size()];
        strArr = arr.toArray(strArr);

        return strArr;
    }
}
