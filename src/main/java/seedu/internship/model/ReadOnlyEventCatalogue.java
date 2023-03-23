package seedu.internship.model;

import javafx.collections.ObservableList;
import seedu.internship.model.event.Event;

/**
 * Unmodifiable view of an Event Catalogue
 */
public interface ReadOnlyEventCatalogue {

    /**
     * Returns an unmodifiable view of the event list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();
}
