package seedu.internship.ui.pages;

import com.calendarfx.model.Entry;
import seedu.internship.model.event.Event;

/**
 * A CalendarEntry created for CalendarFx.
 */
public class CalendarEntry extends Entry<Event> {

    private Event event;

    public CalendarEntry(Event event) {
        super(event.getName().name);
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
