package seedu.address.storage;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static seedu.address.testutil.Assert.assertThrows;
        import static seedu.address.testutil.TypicalEvents.*;

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

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
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
    public void toModelType_validEventWithIdenticalDates_throwsIllegalValueException() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_EVENT_WITH_SAME_DATETIME);
        assertEquals(VALID_EVENT_WITH_SAME_DATETIME, event.toModelType());
    }

    @Test
    public void toModelType_invalidEventDates_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(INVALID_EVENT_DATETIME);
        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }
}
