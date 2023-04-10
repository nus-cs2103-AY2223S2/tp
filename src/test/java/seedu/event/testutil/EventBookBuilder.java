package seedu.event.testutil;

import seedu.event.model.EventBook;
import seedu.event.model.event.Event;

/**
 * A utility class to help with building EventBook objects.
 * Example usage: <br>
 *     {@code EventBook ab = new EventBookBuilder().withEvent("John", "Doe").build();}
 */
public class EventBookBuilder {

    private EventBook eventBook;

    public EventBookBuilder() {
        eventBook = new EventBook();
    }

    public EventBookBuilder(EventBook eventBook) {
        this.eventBook = eventBook;
    }

    /**
     * Adds a new {@code Event} to the {@code EventBook} that we are building.
     */
    public EventBookBuilder withEvent(Event event) {
        eventBook.addEvent(event);
        return this;
    }

    public EventBook build() {
        return eventBook;
    }
}
