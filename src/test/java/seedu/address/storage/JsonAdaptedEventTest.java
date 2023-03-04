package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.BIRTHDAY_PARTY;
import static seedu.address.testutil.TypicalEvents.CARNIVAL;
import static seedu.address.testutil.TypicalEvents.SPORTS_DAY;
import static seedu.address.testutil.TypicalEvents.WEDDING_DINNER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;

public class JsonAdaptedEventTest {
    private static final Event INVALID_EVENT_DATETIME = new Event(
            new EventName("Dinner"), new DateTime("02-02-2023 17:00"), new DateTime("01-02-2023 17:00"));

    private static final Event VALID_EVENT_WITH_SAME_DATETIME = new Event(
            new EventName("Same date range"), new DateTime("02-02-2023 17:00"), new DateTime("02-02-2023 17:00"));

    private static final String INVALID_EVENT_NAME = "小明 21'st Birthday";
    private static final String VALID_EVENT_NAME = "Xiao Ming 21'st Birthday";
    private static final String INVALID_START_DATETIME = "50-02-2023 17:00";
    private static final String INVALID_END_DATETIME = "90-13-1023 17:00";

    @Test
    public void toModelType_validEventObject_returnsEvent() throws Exception {
        JsonAdaptedEvent event1 = new JsonAdaptedEvent(WEDDING_DINNER);
        assertEquals(WEDDING_DINNER, event1.toModelType());
        JsonAdaptedEvent event2 = new JsonAdaptedEvent(CARNIVAL);
        assertEquals(CARNIVAL, event2.toModelType());
        JsonAdaptedEvent event3 = new JsonAdaptedEvent(SPORTS_DAY);
        assertEquals(SPORTS_DAY, event3.toModelType());
        JsonAdaptedEvent event4 = new JsonAdaptedEvent(BIRTHDAY_PARTY);
        assertEquals(BIRTHDAY_PARTY, event4.toModelType());
    }

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event1 = new JsonAdaptedEvent(WEDDING_DINNER.getName().toString(),
                WEDDING_DINNER.getStartDateTime().toString(), WEDDING_DINNER.getEndDateTime().toString());
        assertEquals(WEDDING_DINNER, event1.toModelType());
        JsonAdaptedEvent event2 = new JsonAdaptedEvent(CARNIVAL.getName().toString(),
                CARNIVAL.getStartDateTime().toString(), CARNIVAL.getEndDateTime().toString());
        assertEquals(CARNIVAL, event2.toModelType());
        JsonAdaptedEvent event3 = new JsonAdaptedEvent(SPORTS_DAY.getName().toString(),
                SPORTS_DAY.getStartDateTime().toString(), SPORTS_DAY.getEndDateTime().toString());
        assertEquals(SPORTS_DAY, event3.toModelType());
        JsonAdaptedEvent event4 = new JsonAdaptedEvent(BIRTHDAY_PARTY.getName().toString(),
                BIRTHDAY_PARTY.getStartDateTime().toString(), BIRTHDAY_PARTY.getEndDateTime().toString());
        assertEquals(BIRTHDAY_PARTY, event4.toModelType());
    }

    @Test
    public void toModelType_validEventWithIdenticalDates_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_EVENT_WITH_SAME_DATETIME);
        assertEquals(VALID_EVENT_WITH_SAME_DATETIME, event.toModelType());
    }

    @Test
    public void toModelType_invalidEventDateRange_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(INVALID_EVENT_DATETIME);
        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidEventName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(INVALID_EVENT_NAME,
                WEDDING_DINNER.getStartDateTime().toString(), WEDDING_DINNER.getEndDateTime().toString());
        String expectedMessage = EventName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidDateFormat_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_EVENT_NAME,
                INVALID_START_DATETIME, INVALID_END_DATETIME);
        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }
}
