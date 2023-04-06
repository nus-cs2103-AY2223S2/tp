package ezschedule.model.event;

import static ezschedule.logic.commands.CommandTestUtil.INVALID_DATE;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_END_TIME;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_NAME;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_START_TIME;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_END_TIME_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_NAME_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_START_TIME_B;
import static ezschedule.testutil.Assert.assertThrows;
import static ezschedule.testutil.TypicalEvents.ART;
import static ezschedule.testutil.TypicalEvents.EVENT_A;
import static ezschedule.testutil.TypicalEvents.EVENT_B;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import ezschedule.testutil.EventBuilder;

public class EventTest {

    @Test
    public void isCompleted() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedTodayDate = today.format(dateTimeFormatter);

        Event pastEvent = new EventBuilder(ART).withDate("2020-01-01").withEndTime("00:00").build();
        Event futureEvent = new EventBuilder(ART).withDate("2999-01-01").withEndTime("00:00").build();
        Event pastTodayEvent = new EventBuilder(ART).withDate(formattedTodayDate).withEndTime("00:00").build();
        Event futureTodayEvent = new EventBuilder(ART).withDate(formattedTodayDate).withEndTime("23:59").build();

        assertTrue(pastEvent.isCompleted());
        assertFalse(futureEvent.isCompleted());
        assertTrue(pastTodayEvent.isCompleted());
        assertFalse(futureTodayEvent.isCompleted());
    }

    @Test
    public void isEventOverlap() {
        Event event = new EventBuilder().build();

        // different date -> returns false
        Event otherEvent = new EventBuilder().withDate("2025-01-01").build();
        assertFalse(event.isEventOverlap(otherEvent));

        // same date, earlier time -> returns false
        otherEvent = new EventBuilder().withStartTime("00:00").withEndTime("00:00").build();
        assertFalse(event.isEventOverlap(otherEvent));

        // same date, later time -> returns false
        otherEvent = new EventBuilder().withStartTime("23:59").withEndTime("23:59").build();
        assertFalse(event.isEventOverlap(otherEvent));

        // same date, same start time and end time -> returns true
        otherEvent = new EventBuilder().build();
        assertTrue(event.isEventOverlap(otherEvent));

        // same date, within event time -> returns true
        otherEvent = new EventBuilder().withStartTime("08:01").withEndTime("09:59").build();
        assertTrue(event.isEventOverlap(otherEvent));

        // same date, overlaps start time -> returns true
        otherEvent = new EventBuilder().withStartTime("07:59").withEndTime("08:01").build();
        assertTrue(event.isEventOverlap(otherEvent));

        // same date, overlaps end time -> returns true
        otherEvent = new EventBuilder().withStartTime("09:59").withEndTime("10:01").build();
        assertTrue(event.isEventOverlap(otherEvent));

        // same date, earlier start time and same end time -> returns true
        otherEvent = new EventBuilder().withStartTime("09:59").build();
        assertTrue(event.isEventOverlap(otherEvent));

        // same date, same start time and later end time -> returns true
        otherEvent = new EventBuilder().withEndTime("10:01").build();
        assertTrue(event.isEventOverlap(otherEvent));

        // same date, earlier start time and later end time -> returns true
        otherEvent = new EventBuilder().withStartTime("07:59").withEndTime("10:01").build();
        assertTrue(event.isEventOverlap(otherEvent));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(EVENT_A.equals(EVENT_A));

        // same values -> returns equal
        Event aCopy = new EventBuilder(EVENT_A).build();
        assertTrue(EVENT_A.equals(aCopy));

        // null -> returns false
        assertFalse(EVENT_A.equals(null));

        // different type -> returns false
        assertFalse(EVENT_A.equals(5));

        // different event -> returns false
        assertFalse(EVENT_A.equals(EVENT_B));

        // different name, all other attributes same -> returns not equal
        Event editedA = new EventBuilder(EVENT_A).withName(VALID_NAME_B).build();
        assertFalse(EVENT_A.equals(editedA));

        // different date, all other attributes same -> returns not equal
        editedA = new EventBuilder(EVENT_A).withDate(VALID_DATE_B).build();
        assertFalse(EVENT_A.equals(editedA));

        // different start time, all other attributes same -> returns not equal
        editedA = new EventBuilder(EVENT_A).withStartTime(VALID_START_TIME_B).build();
        assertFalse(EVENT_A.equals(editedA));

        // different end time, all other attributes same -> returns not equal
        editedA = new EventBuilder(EVENT_A).withEndTime(VALID_END_TIME_B).build();
        assertFalse(EVENT_A.equals(editedA));

        // invalid name -> throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new EventBuilder(EVENT_A).withName(INVALID_NAME).build());

        // invalid date -> throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new EventBuilder(EVENT_A).withDate(INVALID_DATE).build());

        // invalid start time -> throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () ->
                new EventBuilder(EVENT_A).withStartTime(INVALID_START_TIME).build());

        // invalid end time -> throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () ->
                new EventBuilder(EVENT_A).withStartTime(INVALID_END_TIME).build());
    }
}
