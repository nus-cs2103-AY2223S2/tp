package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class IsolatedEventListTest {

    private final IsolatedEventList isolatedEventList = new IsolatedEventList();

    private class IsolatedEventStub extends IsolatedEvent {
        public IsolatedEventStub(String eventName, String startDate, String endDate) {
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
        isolatedEventList.insert(new IsolatedEventStub("Biking", "09/03/2023 14:00",
                "09/03/2023 15:00"));
        isolatedEventList.insert(new IsolatedEventStub("Skiing", "09/03/2023 14:00",
                "09/03/2023 15:00"));
        isolatedEventList.insert(new IsolatedEventStub("Canoeing", "09/03/2023 14:00",
                "09/03/2023 15:00"));

        assertEquals("Biking from: 09/03/2023 14:00 to: 09/03/2023 15:00\n"
                + "Canoeing from: 09/03/2023 14:00 to: 09/03/2023 15:00\n"
                + "Skiing from: 09/03/2023 14:00 to: 09/03/2023 15:00\n", isolatedEventList.toString());
    }
}
