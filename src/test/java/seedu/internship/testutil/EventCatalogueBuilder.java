package seedu.internship.testutil;

import seedu.internship.model.EventCatalogue;
import seedu.internship.model.event.Event;

/**
 * A utility class to help with building EventCatalogue objects.
 * Example usage: <br>
 *     {@code EventCatalogue ic = new EventCatalogueBuilder().withEvent("E1").build();}
 */
public class EventCatalogueBuilder {
    private EventCatalogue eventCatalogue;

    public EventCatalogueBuilder() {
        this.eventCatalogue = new EventCatalogue();
    }

    public EventCatalogueBuilder(EventCatalogue eventCatalogue) {
        this.eventCatalogue = eventCatalogue;
    }

    /**
     * Adds a new {@code Event} to the {@code EventCatalogue} that we are building.
     */
    public EventCatalogueBuilder withEvent(Event event) {
        eventCatalogue.addEvent(event);
        return this;
    }

    /**
     * Adds a new {@code Event} to the {@code EventCatalogue} that we are building.
     */
    public EventCatalogueBuilder withEvent(Event[] events) {
        for (Event event : events) {
            eventCatalogue.addEvent(event);
        }
        return this;
    }

    public EventCatalogue build() {
        return eventCatalogue;
    }
}
