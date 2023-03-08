package seedu.address.model.event;

q1import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class IsolatedEventListTest {

    private final IsolatedEventList isolatedEventList = new IsolatedEventList();

    private class IsolatedEventStub extends IsolatedEvent {

        public IsolatedEventStub(String eventName) {
            super(eventName);
        }

        @Override
        public String toString() {
            return this.getEventName();
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
        isolatedEventList.insert(new IsolatedEventStub("Biking"));
        isolatedEventList.insert(new IsolatedEventStub("Skiing"));
        isolatedEventList.insert(new IsolatedEventStub("Canoeing"));

        assertEquals("Biking\n" + "Canoeing\n" + "Skiing\n", isolatedEventList.toString());
    }
}
