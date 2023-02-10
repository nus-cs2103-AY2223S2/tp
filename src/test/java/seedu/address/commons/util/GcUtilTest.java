package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.api.services.calendar.model.Event;

class GcUtilTest {

    @Test
    void getEvents() {
        List<Event> events;
        try {
            events = GcUtil.getEvents();
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
            eventsString = GcUtil.eventsToString();
        } catch (Exception e) {
            fail("Exception thrown: \n\t" + e);
        }
        assertNotNull(eventsString);
        assertFalse(eventsString.isEmpty());
    }
}
