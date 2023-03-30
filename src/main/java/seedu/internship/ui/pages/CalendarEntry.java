package seedu.internship.ui.pages;

import com.calendarfx.model.Entry;

import seedu.internship.model.event.Event;

/**
 * A custom {@code Entry} created for CalendarFx.
 */
public class CalendarEntry extends Entry<Event> {

    private Event event;

    /**
     * A constructor for {@code CalendarEntry}
     * @param event An {@code Event} for this {@code Entry}
     */
    public CalendarEntry(Event event) {
        super(event.getName().name);
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
