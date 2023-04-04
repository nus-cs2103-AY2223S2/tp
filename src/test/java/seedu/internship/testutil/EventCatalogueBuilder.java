package seedu.internship.testutil;


import seedu.internship.model.EventCatalogue;
import seedu.internship.model.event.Event;

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

    public EventCatalogue build() {
        return eventCatalogue;
    }
}
