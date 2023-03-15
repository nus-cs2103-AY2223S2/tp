package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.SampleDateTimeUtil.THREE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.TWO_O_CLOCK_VALID;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class IsolatedEventListTest {

    private final IsolatedEventList isolatedEventList = new IsolatedEventList();

    private static class IsolatedEventStub extends IsolatedEvent {
        public IsolatedEventStub(String eventName, LocalDateTime startDate, LocalDateTime endDate) {
            super(eventName, startDate, endDate);
        }

        @Override
        public int compareTo(IsolatedEvent o) {
            return this.getEventName().compareTo(o.getEventName());
        }
    }
    @Test
    void insert_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> isolatedEventList.insert(null));
    }

    @Test
    void testToString() {
        isolatedEventList.insert(new IsolatedEventStub("Biking", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID));
        isolatedEventList.insert(new IsolatedEventStub("Skiing", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID));
        isolatedEventList.insert(new IsolatedEventStub("Canoeing", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID));

        assertEquals("Biking from: 09/03/2023 14:00 to: 09/03/2023 15:00\n"
                + "Canoeing from: 09/03/2023 14:00 to: 09/03/2023 15:00\n"
                + "Skiing from: 09/03/2023 14:00 to: 09/03/2023 15:00\n", isolatedEventList.toString());
    }

    @Test
    void contain() {
        isolatedEventList.insert(new IsolatedEventStub("Biking", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID));
        isolatedEventList.insert(new IsolatedEventStub("Skiing", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID));
        isolatedEventList.insert(new IsolatedEventStub("Canoeing", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID));

        assertTrue(isolatedEventList.contain(new IsolatedEventStub("Biking", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID)));

        assertFalse(isolatedEventList.contain(new IsolatedEventStub("sleep", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID)));
    }

    @Test
    void deleteIsolatedEvent() {
        IsolatedEvent event = new IsolatedEventStub("Biking", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID);
        isolatedEventList.insert(event);
        assertTrue(isolatedEventList.contain(event));
        isolatedEventList.deleteIsolatedEvent(event);
        assertFalse(isolatedEventList.contain(event));
    }

    @Test
    void getIsolatedEvent() {
        isolatedEventList.insert(new IsolatedEventStub("Biking", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID));
        isolatedEventList.insert(new IsolatedEventStub("Skiing", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID));
        isolatedEventList.insert(new IsolatedEventStub("Canoeing", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID));

        assertEquals("Biking from: 09/03/2023 14:00 to: 09/03/2023 15:00",
                isolatedEventList.getIsolatedEvent(0).toString());

        assertEquals("Canoeing from: 09/03/2023 14:00 to: 09/03/2023 15:00",
                isolatedEventList.getIsolatedEvent(1).toString());

        assertEquals("Skiing from: 09/03/2023 14:00 to: 09/03/2023 15:00",
                isolatedEventList.getIsolatedEvent(2).toString());
    }

    @Test
    void edit() {
        IsolatedEvent event = new IsolatedEventStub("Skiing", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID);
        isolatedEventList.insert(event);
        IsolatedEvent editedEvent = new IsolatedEventStub("Canoeing", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID);
        isolatedEventList.edit(event, editedEvent);
        assertEquals(isolatedEventList.getIsolatedEvent(0).toString(), editedEvent.toString());
    }
}
