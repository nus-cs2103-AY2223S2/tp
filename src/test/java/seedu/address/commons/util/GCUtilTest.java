package seedu.address.commons.util;

import com.google.api.services.calendar.model.Event;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GCUtilTest {

    @Test
    void getEvents() {
        List<Event> events;
        try {
            events = GCUtil.getEvents();
        } catch (Exception e) {
            fail("Exception thrown: \n\t" + e);
            return;
        }
        assertNotNull(events);
    }

    @Test
    void eventsToString() {
        String eventsString = null;
        try {
            eventsString = GCUtil.eventsToString();
        } catch (Exception e) {
            fail("Exception thrown: \n\t" + e);
        }
        assertNotNull(eventsString);
        assertFalse(eventsString.isEmpty());
    }
}