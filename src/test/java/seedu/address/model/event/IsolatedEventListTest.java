package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.SampleDateTimeUtil.VALID_END_DATETIME;
import static seedu.address.testutil.SampleDateTimeUtil.VALID_START_DATETIME;

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
        isolatedEventList.insert(new IsolatedEventStub("Biking", VALID_START_DATETIME,
                VALID_END_DATETIME));
        isolatedEventList.insert(new IsolatedEventStub("Skiing", VALID_START_DATETIME,
                VALID_END_DATETIME));
        isolatedEventList.insert(new IsolatedEventStub("Canoeing", VALID_START_DATETIME,
                VALID_END_DATETIME));

        assertEquals("Biking from: 09/03/2023 14:00 to: 09/03/2023 15:00\n"
                + "Canoeing from: 09/03/2023 14:00 to: 09/03/2023 15:00\n"
                + "Skiing from: 09/03/2023 14:00 to: 09/03/2023 15:00\n", isolatedEventList.toString());
    }

    @Test
    void contain() {
        isolatedEventList.insert(new IsolatedEventStub("Biking", VALID_START_DATETIME, VALID_END_DATETIME));
        isolatedEventList.insert(new IsolatedEventStub("Skiing", VALID_START_DATETIME, VALID_END_DATETIME));
        isolatedEventList.insert(new IsolatedEventStub("Canoeing", VALID_START_DATETIME, VALID_END_DATETIME));

        assertTrue(isolatedEventList.contain(new IsolatedEventStub("Biking", VALID_START_DATETIME,
                VALID_END_DATETIME)));

        assertFalse(isolatedEventList.contain(new IsolatedEventStub("sleep", VALID_START_DATETIME,
                VALID_END_DATETIME)));
    }
}
