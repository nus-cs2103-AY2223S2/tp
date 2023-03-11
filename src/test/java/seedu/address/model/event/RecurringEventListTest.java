package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class RecurringEventListTest {

    private final RecurringEventList recurringEventList = new RecurringEventList();

    private class RecurringEventStub extends RecurringEvent {

        public RecurringEventStub(String eventName) {
            super(eventName);
        }

        @Override
        public String toString() {
            return this.getEventName();
        }

        @Override
        public int compareTo(RecurringEvent o) {
            return this.getEventName().compareTo(o.getEventName());
        }
    }
    @Test
    void insert_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recurringEventList.insert(null));
    }

    @Test
    void testToString() {
        recurringEventList.insert(new RecurringEventStub("Biking"));
        recurringEventList.insert(new RecurringEventStub("Skiing"));
        recurringEventList.insert(new RecurringEventStub("Canoeing"));

        assertEquals("Biking\n" + "Canoeing\n" + "Skiing\n", recurringEventList.toString());
    }
}
